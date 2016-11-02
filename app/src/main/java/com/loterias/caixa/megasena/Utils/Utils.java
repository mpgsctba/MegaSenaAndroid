package com.loterias.caixa.megasena.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.loterias.caixa.megasena.R;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Marcos on 10/10/2015.
 */
public class Utils {

    public Utils() {

    }

    public static String formateDate(Date data) {
        if (data == null) return "";
        return (String) android.text.format.DateFormat.format("dd/MM/yyyy", data);
    }

    public static String formateValor(BigDecimal valor) {
        if (valor == null) return "";
        return (String) NumberFormat.getCurrencyInstance().format(valor);
    }

    public static int findImagemIdByNumero(Integer numero) {

        if (numero < 10) {
            if (numero == 1) return R.drawable.numero_01;
            if (numero == 2) return R.drawable.numero_02;
            if (numero == 3) return R.drawable.numero_03;
            if (numero == 4) return R.drawable.numero_04;
            if (numero == 5) return R.drawable.numero_05;
            if (numero == 6) return R.drawable.numero_06;
            if (numero == 7) return R.drawable.numero_07;
            if (numero == 8) return R.drawable.numero_08;
            if (numero == 9) return R.drawable.numero_09;
        } else if (numero < 20) {
            if (numero == 10) return R.drawable.numero_10;
            if (numero == 11) return R.drawable.numero_11;
            if (numero == 12) return R.drawable.numero_12;
            if (numero == 13) return R.drawable.numero_13;
            if (numero == 14) return R.drawable.numero_14;
            if (numero == 15) return R.drawable.numero_15;
            if (numero == 16) return R.drawable.numero_16;
            if (numero == 17) return R.drawable.numero_17;
            if (numero == 18) return R.drawable.numero_18;
            if (numero == 19) return R.drawable.numero_19;
        } else if (numero < 30) {
            if (numero == 20) return R.drawable.numero_20;
            if (numero == 21) return R.drawable.numero_21;
            if (numero == 22) return R.drawable.numero_22;
            if (numero == 23) return R.drawable.numero_23;
            if (numero == 24) return R.drawable.numero_24;
            if (numero == 25) return R.drawable.numero_25;
            if (numero == 26) return R.drawable.numero_26;
            if (numero == 27) return R.drawable.numero_27;
            if (numero == 28) return R.drawable.numero_28;
            if (numero == 29) return R.drawable.numero_29;
        } else if (numero < 40) {
            if (numero == 30) return R.drawable.numero_30;
            if (numero == 31) return R.drawable.numero_31;
            if (numero == 32) return R.drawable.numero_32;
            if (numero == 33) return R.drawable.numero_33;
            if (numero == 34) return R.drawable.numero_34;
            if (numero == 35) return R.drawable.numero_35;
            if (numero == 36) return R.drawable.numero_36;
            if (numero == 37) return R.drawable.numero_37;
            if (numero == 38) return R.drawable.numero_38;
            if (numero == 39) return R.drawable.numero_39;
        } else if (numero < 50) {
            if (numero == 40) return R.drawable.numero_40;
            if (numero == 41) return R.drawable.numero_41;
            if (numero == 42) return R.drawable.numero_42;
            if (numero == 43) return R.drawable.numero_43;
            if (numero == 44) return R.drawable.numero_44;
            if (numero == 45) return R.drawable.numero_45;
            if (numero == 46) return R.drawable.numero_46;
            if (numero == 47) return R.drawable.numero_47;
            if (numero == 48) return R.drawable.numero_48;
            if (numero == 49) return R.drawable.numero_49;
        } else if (numero < 60) {
            if (numero == 50) return R.drawable.numero_50;
            if (numero == 51) return R.drawable.numero_51;
            if (numero == 52) return R.drawable.numero_52;
            if (numero == 53) return R.drawable.numero_53;
            if (numero == 54) return R.drawable.numero_54;
            if (numero == 55) return R.drawable.numero_55;
            if (numero == 56) return R.drawable.numero_56;
            if (numero == 57) return R.drawable.numero_57;
            if (numero == 58) return R.drawable.numero_58;
            if (numero == 59) return R.drawable.numero_59;
        }
        return R.drawable.numero_60;
    }

    public static Date extrairData(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar dateCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        return dateCalendar.getTime();
    }

    public static boolean verificaConexao(Context context) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }}
