package com.loterias.caixa.megasena.WebService;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.client.params.CookiePolicy;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.EntityUtils;


public class WebService {

    DefaultHttpClient httpClient;
    HttpContext localContext;
    private String ret;

    HttpResponse response = null;
    HttpPost httpPost = null;
    HttpGet httpGet = null;
    String webServiceUrl;

    //The serviceName should be the name of the Service you are going to be using.
    public WebService(String serviceName){
        HttpParams myParams = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);
        httpClient = new DefaultHttpClient(myParams);
        localContext = new BasicHttpContext();
        webServiceUrl = serviceName;

    }

    //Use this method to do a HttpPost\WebInvoke on a Web Service
    public String webInvoke(String methodName, Map<String, Object> params) {

        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, Object> param : params.entrySet()){
            try {
                jsonObject.put(param.getKey(), param.getValue());
            }
            catch (JSONException e) {
                Log.e("MEGA", "JSONException : "+e);
            }
        }
        return webInvoke(methodName,  jsonObject.toString(), "application/json");
    }

    private String webInvoke(String methodName, String data, String contentType) {
        ret = null;

        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);

        httpPost = new HttpPost(webServiceUrl + methodName);
        response = null;

        StringEntity tmp = null;

        //httpPost.setHeader("User-Agent", "SET YOUR USER AGENT STRING HERE");
        httpPost.setHeader("Accept",
                "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

        if (contentType != null) {
            httpPost.setHeader("Content-Type", contentType);
        } else {
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        }

        try {
            tmp = new StringEntity(data,"UTF-8");
        } catch (Exception e) {
            Log.e("MEGA", "HttpUtils : UnsupportedEncodingException : "+e);
        }

        httpPost.setEntity(tmp);

        Log.d("MEGA", webServiceUrl + "?" + data);

        try {
            response = httpClient.execute(httpPost,localContext);

            if (response != null) {
                ret = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            Log.e("MEGA", "HttpUtils: " + e);
        }

        return ret;
    }

    //Use this method to do a HttpGet/WebGet on the web service
    public String webGet(String methodName, Map<String, String> params) {
        String getUrl = webServiceUrl + methodName;

        int i = 0;
        for (Map.Entry<String, String> param : params.entrySet())
        {
            if(i == 0){
                getUrl += "?";
            }
            else{
                getUrl += "&";
            }

            try {
                getUrl += param.getKey() + "=" + URLEncoder.encode(param.getValue(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.e("MEGA", e.getMessage());
            }

            i++;
        }
        Log.i("MEGA", "getUrl: " + getUrl);
        try {
            Log.i("MEGA", "01.");
            httpGet = new HttpGet(getUrl);
            Log.i("MEGA", "02.");
            response = httpClient.execute(httpGet);
            Log.i("MEGA", "passou response ws.");
        } catch (Exception e) {
            Log.i("MEGA", "erro depois do execute: "+e.getMessage());
            //Log.i("MEGA:", e.getMessage().toString());
        }

        // we assume that the response body contains the error message
        try {
            ret = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            Log.e("MEGA", e.getMessage());
        }
        return ret;
    }

    //Use this method to do a HttpGet/WebGet on the web service
    public String doPost(String methodName, List<NameValuePair> params) {
        String getUrl = webServiceUrl + methodName;






        httpPost = new HttpPost(getUrl);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        }

        Log.e("MEGA: ",getUrl);


        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            Log.e("MEGA:", e.getMessage());
        }

        // we assume that the response body contains the error message
        try {
            ret = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            Log.e("MEGA:", e.getMessage());
        }

        return ret;
    }


    public InputStream getHttpStream(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception e) {
            throw new IOException("Error connecting");
        } // end try-catch

        return in;
    }

    public void clearCookies() {
        httpClient.getCookieStore().clear();
    }

    public void abort() {
        try {
            if (httpClient != null) {
                //System.out.println("Abort.");
                httpPost.abort();
            }
        } catch (Exception e) {
            //System.out.println("Your App Name Here" + e);
        }
    }
}