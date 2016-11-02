package com.loterias.caixa.megasena.Acitivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends Activity implements Runnable {

    private final String url = "http://confiraloterias.com.br/api/json/";
    private final String loteria = "megasena";
    private final String token = "9yNMw5h0K2QTUEC";

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        atualizarDados();
//        enviarNotificacoes();
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }


    private void atualizarDados() {
        try {
            new Thread() {
                public void run() {
                    try {
                        BancoDeDados bd = new BancoDeDados(getApplicationContext());
                        WebService ws = new WebService(url);
                        Map<String, String> param = new HashMap<String, String>();
                        bd.abrir();
                        Resultado resultado = bd.getUltimoConsurso();
                        bd.fechar();
                        Integer ultimoBanco = resultado.getConcursoId();
                        param.put("loteria", loteria);
                        param.put("token",token);
                        String response = ws.webGet("", param);
                        JSONObject retorno = new JSONObject(response);
                        JSONObject concurso =  (JSONObject)retorno.get("concurso");
                        Integer ultimoServidor = Integer.parseInt((String) concurso.get("numero"));

                        if (ultimoServidor > ultimoBanco) {
                            bd.inserirRetorno(retorno);
                            ultimoServidor--;
                        }

                        while (ultimoBanco < ultimoServidor) {
                            param.put("concurso", ultimoServidor.toString());
                            response = ws.webGet("", param);
                            retorno = new JSONObject(response);
                            bd.inserirRetorno(retorno);
                            ultimoServidor--;
                        }

                    }catch (Exception e) {
                        Log.e("MEGA", "erro1:"+e.getMessage());
                    }
                }
            }.start();
        }catch (Exception e) {
            Log.e("MEGA", e.getMessage());
        }
    }

    private void enviarNotificacoes() {
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_MEGA_SENA"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarmeAtivo){
            Log.i("MEGA", "Novo alarme");

            Intent intent = new Intent("ALARME_MEGA_SENA");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            if (c.get(Calendar.HOUR_OF_DAY) > 21) {
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
            Integer year = c.get(Calendar.YEAR);
            Integer month = c.get(Calendar.MONTH);
            Integer day = c.get(Calendar.DAY_OF_MONTH);
            Integer hour = c.get(Calendar.HOUR_OF_DAY);
            Integer minute = c.get(Calendar.MINUTE);
            c.set(year, month, day, 21, 5);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 86400000, p);
        }
        else{
            Log.i("MEGA", "Alarme ja ativo");
        }


    }

    // Exibe a notificacao
    protected void criarNotificacao(Context context, CharSequence mensagemBarraStatus,
                                    CharSequence titulo, CharSequence mensagem, Class activity) {
        // Recupera o servi�o do NotificationManager
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification n = new Notification(R.drawable.ic_clover, mensagemBarraStatus, System.currentTimeMillis());

        // PendingIntent para executar a Activity se o usu�rio selecionar a notifica��o
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, activity), 0);

        // Flag utilizada para remover a notifica��o da barra de status
        // quando o usu�rio clicar nela
        n.flags |= Notification.FLAG_AUTO_CANCEL;

        // Informa��es
//        n.setLatestEventInfo(this, titulo, mensagem, p);

        // Espera 100ms e vibra por 250ms, espera por mais 100ms e vibra por 500ms
        n.vibrate = new long[] { 100, 250, 100, 500 };

        // id da notificacao
        nm.notify(R.string.app_name, n);
    }

}
