package com.loterias.caixa.megasena.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.loterias.caixa.megasena.Model.Acertos;
import com.loterias.caixa.megasena.Model.Ranking;
import com.loterias.caixa.megasena.Model.Resultado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Marcos on 10/10/2015.
 */
public class BancoDeDados {

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;
    private static ArrayList<Resultado> resultados;
    private static ArrayList<Ranking> rankings;
    private static ArrayList<Ranking> rankingsNumero;
    private static ArrayList<Ranking> rankingsNaoSai;
    private static ArrayList<Ranking> rankingsNaoSaiPorNumero;

    public BancoDeDados(Context ctx){
        this.context = ctx;
        openHelper = new MeuOpenHelper(context);
    }

    public BancoDeDados abrir(){
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar(){
        openHelper.close();
    }

    private Cursor getResultadosCursor(){
        return db.query("Resultados", new String[]{
                        "concurso", "data", "dezena1", "dezena2", "dezena3", "dezena4", "dezena5", "dezena6", "arrecadacaoTotal", "ganhadoresSena",
                        "rateioSena", "ganhadoresQuina", "rateioQuina", "ganhadoresQuadra", "rateioQuadra", "acumulado", "valorAcumulado",
                        "estimativaPremio", "acumuladoMegaVirada", "proximoSorteio"},
                null, null, null, null, "concurso DESC");
    }

    private Cursor getResultadoCursor(String valor){
        return db.query("Resultados", new String[]{
                        "concurso", "data", "dezena1", "dezena2", "dezena3", "dezena4", "dezena5", "dezena6", "arrecadacaoTotal", "ganhadoresSena",
                        "rateioSena", "ganhadoresQuina", "rateioQuina", "ganhadoresQuadra", "rateioQuadra", "acumulado", "valorAcumulado",
                        "estimativaPremio", "acumuladoMegaVirada", "proximoSorteio"},
                "concurso=?", new String[]{valor}, null, null, "concurso DESC");
    }

    private Cursor getProximosResultadoCursor(String valor){
        return db.query("Resultados", new String[]{
                        "concurso", "data", "dezena1", "dezena2", "dezena3", "dezena4", "dezena5", "dezena6", "arrecadacaoTotal", "ganhadoresSena",
                        "rateioSena", "ganhadoresQuina", "rateioQuina", "ganhadoresQuadra", "rateioQuadra", "acumulado", "valorAcumulado",
                        "estimativaPremio", "acumuladoMegaVirada", "proximoSorteio"},
                "concurso > ?", new String[]{valor}, null, null, "concurso ASC", "10");
    }

    private Cursor getUltimoResultadoCursor(){
        return db.query("Resultados", new String[]{
                        "concurso", "data", "dezena1", "dezena2", "dezena3", "dezena4", "dezena5", "dezena6", "arrecadacaoTotal", "ganhadoresSena",
                        "rateioSena", "ganhadoresQuina", "rateioQuina", "ganhadoresQuadra", "rateioQuadra", "acumulado", "valorAcumulado",
                        "estimativaPremio", "acumuladoMegaVirada", "proximoSorteio"},
                null, null, null, null, "concurso DESC", "1");
    }


    private Cursor getRankingNumerosCursor(){
        String query =
                "SELECT DISTINCT a.dezena1 AS numero, " +
                "(SELECT count(*) " +
                "FROM RESULTADOS b " +
                "WHERE b.dezena1 = a.dezena1 OR b.dezena2 = a.dezena1 OR b.dezena3 = a.dezena1 " +
                "OR b.dezena4 = a.dezena1 OR b.dezena5 = a.dezena1 OR b.dezena6 = a.dezena1) AS quantidade " +
                "FROM RESULTADOS a " +
                "UNION " +
                "SELECT DISTINCT a.dezena6 AS numero, " +
                "(SELECT count(*) " +
                "FROM RESULTADOS b " +
                "WHERE b.dezena1 = a.dezena6 OR b.dezena2 = a.dezena6 OR b.dezena3 = a.dezena6 " +
                "OR b.dezena4 = a.dezena6 OR b.dezena5 = a.dezena6 OR b.dezena6 = a.dezena6) AS quantidade " +
                "FROM RESULTADOS a " +
                "ORDER BY quantidade DESC";
        return db.rawQuery(query, null);

        /*query("Resultados", new String[]{
                        "concurso", "data", "dezena1", "dezena2", "dezena3", "dezena4", "dezena5", "dezena6", "arrecadacaoTotal", "ganhadoresSena",
                        "rateioSena", "ganhadoresQuina", "rateioQuina", "ganhadoresQuadra", "rateioQuadra", "acumulado", "valorAcumulado",
                        "estimativaPremio", "acumuladoMegaVirada"},
                "concurso=?", new String[]{valor}, null, null, "concurso DESC");*/
    }

    private Cursor getAcertosSena(Integer dezena1, Integer dezena2, Integer dezena3, Integer dezena4, Integer dezena5,
                                  Integer dezena6, Integer dezena7, Integer dezena8, Integer dezena9, Integer dezena10,
                                  Integer dezena11, Integer dezena12, Integer dezena13, Integer dezena14, Integer dezena15){
        String query =
            "select sum(valor) as valor, concurso, data " +
            "from ( " +
            "SELECT SUM(1) as valor, concurso , data " +
            "FROM Resultados " +
            "WHERE dezena1 IN (" + dezena1 + ", " + dezena2 + ", " + dezena3 + ", " + dezena4 + ", " + dezena5 + ", " + dezena6 + ", " + dezena7 + ", " + dezena8 + ", " + dezena9 + ", " + dezena10 + ", " + dezena11 + ", " + dezena12 + ", " + dezena13 + ", " + dezena14 + ", " + dezena15 + ") " +
            "group by concurso, data " +
            "UNION ALL " +
            "SELECT SUM(1) as valor, concurso, data " +
            "FROM Resultados " +
            "WHERE dezena2 IN (" + dezena1 + ", " + dezena2 + ", " + dezena3 + ", " + dezena4 + ", " + dezena5 + ", " + dezena6 + ", " + dezena7 + ", " + dezena8 + ", " + dezena9 + ", " + dezena10 + ", " + dezena11 + ", " + dezena12 + ", " + dezena13 + ", " + dezena14 + ", " + dezena15 + ") " +
            "group by concurso, data " +
            "UNION ALL " +
            "SELECT SUM(1) as valor, concurso, data " +
            "FROM Resultados " +
            "WHERE dezena3 IN (" + dezena1 + ", " + dezena2 + ", " + dezena3 + ", " + dezena4 + ", " + dezena5 + ", " + dezena6 + ", " + dezena7 + ", " + dezena8 + ", " + dezena9 + ", " + dezena10 + ", " + dezena11 + ", " + dezena12 + ", " + dezena13 + ", " + dezena14 + ", " + dezena15 + ") " +
            "group by concurso, data " +
            "UNION ALL " +
            "SELECT SUM(1) as valor, concurso, data " +
            "FROM Resultados " +
            "WHERE dezena4 IN (" + dezena1 + ", " + dezena2 + ", " + dezena3 + ", " + dezena4 + ", " + dezena5 + ", " + dezena6 + ", " + dezena7 + ", " + dezena8 + ", " + dezena9 + ", " + dezena10 + ", " + dezena11 + ", " + dezena12 + ", " + dezena13 + ", " + dezena14 + ", " + dezena15 + ") " +
            "group by concurso, data " +
            "UNION ALL " +
            "SELECT SUM(1) as valor, concurso, data " +
            "FROM Resultados " +
            "WHERE dezena5 IN (" + dezena1 + ", " + dezena2 + ", " + dezena3 + ", " + dezena4 + ", " + dezena5 + ", " + dezena6 + ", " + dezena7 + ", " + dezena8 + ", " + dezena9 + ", " + dezena10 + ", " + dezena11 + ", " + dezena12 + ", " + dezena13 + ", " + dezena14 + ", " + dezena15 + ") " +
            "group by concurso, data " +
            "UNION ALL " +
            "SELECT SUM(1) as valor, concurso, data " +
            "FROM Resultados " +
            "WHERE dezena6 IN (" + dezena1 + ", " + dezena2 + ", " + dezena3 + ", " + dezena4 + ", " + dezena5 + ", " + dezena6 + ", " + dezena7 + ", " + dezena8 + ", " + dezena9 + ", " + dezena10 + ", " + dezena11 + ", " + dezena12 + ", " + dezena13 + ", " + dezena14 + ", " + dezena15 + ") " +
            "group by concurso, data " +
            ") result " +
            "group by concurso, data " +
            "HAVING sum(valor) >= 4 " +
            "ORDER BY valor DESC, concurso ASC";

        return db.rawQuery(query, null);
    }

    public Resultado getUltimoConsurso() {
        Resultado resultado = new Resultado();
        Cursor cursor = getUltimoResultadoCursor();
        if(cursor.moveToFirst())
            do{
                resultado.setConcursoId(cursor.getInt(cursor.getColumnIndex("concurso")));
                String data = cursor.getString(cursor.getColumnIndex("data"));
                Date date;
                String proximoSorteio = cursor.getString(cursor.getColumnIndex("proximoSorteio"));
                Date proximoSorteioAux;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    date = format.parse(data);
                    resultado.setDataSorteio(date);
                    proximoSorteioAux = format.parse(proximoSorteio);
                    resultado.setProximoSorteio(proximoSorteioAux);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                resultado.setDezena1(cursor.getInt(cursor.getColumnIndex("dezena1")));
                resultado.setDezena2(cursor.getInt(cursor.getColumnIndex("dezena2")));
                resultado.setDezena3(cursor.getInt(cursor.getColumnIndex("dezena3")));
                resultado.setDezena4(cursor.getInt(cursor.getColumnIndex("dezena4")));
                resultado.setDezena5(cursor.getInt(cursor.getColumnIndex("dezena5")));
                resultado.setDezena6(cursor.getInt(cursor.getColumnIndex("dezena6")));
                resultado.setAcumuladoMegaVirada(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("acumuladoMegaVirada"))));
                resultado.setArrecadacaoTotal(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("arrecadacaoTotal"))));
                resultado.setEstimativaPremio(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("estimativaPremio"))));
                resultado.setValorAcumulado(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("valorAcumulado"))));
                resultado.setGanhadoresSena(cursor.getInt(cursor.getColumnIndex("ganhadoresSena")));
                resultado.setGanhadoresQuina(cursor.getInt(cursor.getColumnIndex("ganhadoresQuina")));
                resultado.setGanhadoresQuadra(cursor.getInt(cursor.getColumnIndex("ganhadoresQuadra")));
                resultado.setAcumulado((cursor.getShort(cursor.getColumnIndex("acumulado")) == 1 ? true : false));
                resultado.setRateioSena(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioSena"))));
                resultado.setRateioQuina(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuina"))));
                resultado.setRateioQuadra(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuadra"))));
            }while(cursor.moveToNext());


        return resultado;
    }

    public Resultado getResultado(Integer concursoId) {
        Resultado resultado = null;
        Cursor cursor = getResultadoCursor(concursoId.toString());
        if (cursor == null) return null;
        if(cursor.moveToFirst())
            do{
                resultado = new Resultado();
                resultado.setConcursoId(cursor.getInt(cursor.getColumnIndex("concurso")));
                String data = cursor.getString(cursor.getColumnIndex("data"));
                Date date;
                String proximoSorteio = cursor.getString(cursor.getColumnIndex("proximoSorteio"));
                Date proximoSorteioAux;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    date = format.parse(data);
                    resultado.setDataSorteio(date);
                    proximoSorteioAux = format.parse(proximoSorteio);
                    resultado.setProximoSorteio(proximoSorteioAux);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                resultado.setDezena1(cursor.getInt(cursor.getColumnIndex("dezena1")));
                resultado.setDezena2(cursor.getInt(cursor.getColumnIndex("dezena2")));
                resultado.setDezena3(cursor.getInt(cursor.getColumnIndex("dezena3")));
                resultado.setDezena4(cursor.getInt(cursor.getColumnIndex("dezena4")));
                resultado.setDezena5(cursor.getInt(cursor.getColumnIndex("dezena5")));
                resultado.setDezena6(cursor.getInt(cursor.getColumnIndex("dezena6")));
                resultado.setAcumuladoMegaVirada(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("acumuladoMegaVirada"))));
                resultado.setArrecadacaoTotal(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("arrecadacaoTotal"))));
                resultado.setEstimativaPremio(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("estimativaPremio"))));
                resultado.setValorAcumulado(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("valorAcumulado"))));
                resultado.setGanhadoresSena(cursor.getInt(cursor.getColumnIndex("ganhadoresSena")));
                resultado.setGanhadoresQuina(cursor.getInt(cursor.getColumnIndex("ganhadoresQuina")));
                resultado.setGanhadoresQuadra(cursor.getInt(cursor.getColumnIndex("ganhadoresQuadra")));
                resultado.setAcumulado((cursor.getShort(cursor.getColumnIndex("acumulado")) == 1 ? true : false));
                resultado.setRateioSena(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioSena"))));
                resultado.setRateioQuina(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuina"))));
                resultado.setRateioQuadra(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuadra"))));
            }while(cursor.moveToNext());


        return resultado;
    }

    public ArrayList<Resultado> getResultados() {
        if (resultados != null && resultados.size() >0) return resultados;
        resultados = new ArrayList<Resultado>();
        Cursor cursor = getResultadosCursor();

        if(cursor.moveToFirst())
            do{
                Resultado resultado = new Resultado();
                resultado.setConcursoId(cursor.getInt(cursor.getColumnIndex("concurso")));
                String data = cursor.getString(cursor.getColumnIndex("data"));
                Date date;
                String proximoSorteio = cursor.getString(cursor.getColumnIndex("proximoSorteio"));
                Date proximoSorteioAux;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    date = format.parse(data);
                    resultado.setDataSorteio(date);
                    proximoSorteioAux = format.parse(proximoSorteio);
                    resultado.setProximoSorteio(proximoSorteioAux);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                resultado.setDezena1(cursor.getInt(cursor.getColumnIndex("dezena1")));
                resultado.setDezena2(cursor.getInt(cursor.getColumnIndex("dezena2")));
                resultado.setDezena3(cursor.getInt(cursor.getColumnIndex("dezena3")));
                resultado.setDezena4(cursor.getInt(cursor.getColumnIndex("dezena4")));
                resultado.setDezena5(cursor.getInt(cursor.getColumnIndex("dezena5")));
                resultado.setDezena6(cursor.getInt(cursor.getColumnIndex("dezena6")));
                resultado.setAcumuladoMegaVirada(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("acumuladoMegaVirada"))));
                resultado.setArrecadacaoTotal(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("arrecadacaoTotal"))));
                resultado.setEstimativaPremio(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("estimativaPremio"))));
                resultado.setValorAcumulado(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("valorAcumulado"))));
                resultado.setGanhadoresSena(cursor.getInt(cursor.getColumnIndex("ganhadoresSena")));
                resultado.setGanhadoresQuina(cursor.getInt(cursor.getColumnIndex("ganhadoresQuina")));
                resultado.setGanhadoresQuadra(cursor.getInt(cursor.getColumnIndex("ganhadoresQuadra")));
                resultado.setAcumulado((cursor.getShort(cursor.getColumnIndex("acumulado")) == 1 ? true : false));
                resultado.setRateioSena(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioSena"))));
                resultado.setRateioQuina(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuina"))));
                resultado.setRateioQuadra(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuadra"))));
                resultados.add(resultado);
            }while(cursor.moveToNext());
        return resultados;
    }

    public ArrayList<Resultado> getResultados100(String ultimo) {
        resultados = new ArrayList<Resultado>();
        Cursor cursor = getProximosResultadoCursor(ultimo);

        if(cursor.moveToFirst())
            do{
                Resultado resultado = new Resultado();
                resultado.setConcursoId(cursor.getInt(cursor.getColumnIndex("concurso")));
                String data = cursor.getString(cursor.getColumnIndex("data"));
                Date date;
                String proximoSorteio = cursor.getString(cursor.getColumnIndex("proximoSorteio"));
                Date proximoSorteioAux;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    date = format.parse(data);
                    resultado.setDataSorteio(date);
                    proximoSorteioAux = format.parse(proximoSorteio);
                    resultado.setProximoSorteio(proximoSorteioAux);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                resultado.setDezena1(cursor.getInt(cursor.getColumnIndex("dezena1")));
                resultado.setDezena2(cursor.getInt(cursor.getColumnIndex("dezena2")));
                resultado.setDezena3(cursor.getInt(cursor.getColumnIndex("dezena3")));
                resultado.setDezena4(cursor.getInt(cursor.getColumnIndex("dezena4")));
                resultado.setDezena5(cursor.getInt(cursor.getColumnIndex("dezena5")));
                resultado.setDezena6(cursor.getInt(cursor.getColumnIndex("dezena6")));
                resultado.setAcumuladoMegaVirada(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("acumuladoMegaVirada"))));
                resultado.setArrecadacaoTotal(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("arrecadacaoTotal"))));
                resultado.setEstimativaPremio(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("estimativaPremio"))));
                resultado.setValorAcumulado(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("valorAcumulado"))));
                resultado.setGanhadoresSena(cursor.getInt(cursor.getColumnIndex("ganhadoresSena")));
                resultado.setGanhadoresQuina(cursor.getInt(cursor.getColumnIndex("ganhadoresQuina")));
                resultado.setGanhadoresQuadra(cursor.getInt(cursor.getColumnIndex("ganhadoresQuadra")));
                resultado.setAcumulado((cursor.getShort(cursor.getColumnIndex("acumulado")) == 1 ? true : false));
                resultado.setRateioSena(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioSena"))));
                resultado.setRateioQuina(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuina"))));
                resultado.setRateioQuadra(BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex("rateioQuadra"))));
                resultados.add(resultado);
            }while(cursor.moveToNext());
        return resultados;
    }

    public ArrayList<Ranking> getRanking() {
        if (rankings != null && rankings.size() >0) return rankings;
        rankings = new ArrayList<Ranking>();
        int[] list = new int[60];
        ArrayList<Resultado> resultados = getResultados();
        for (Resultado resultado: resultados) {
            Integer dezena1 = resultado.getDezena1();
            Integer dezena2 = resultado.getDezena2();
            Integer dezena3 = resultado.getDezena3();
            Integer dezena4 = resultado.getDezena4();
            Integer dezena5 = resultado.getDezena5();
            Integer dezena6 = resultado.getDezena6();
            list[dezena1-1] +=1;
            list[dezena2-1] +=1;
            list[dezena3-1] +=1;
            list[dezena4-1] +=1;
            list[dezena5-1] +=1;
            list[dezena6-1] +=1;
        }
        for(int i=0; i<60; i++) {
            Ranking ranking = new Ranking();
            ranking.setNumero(i +1);
            ranking.setQuantidade(list[i]);
            rankings.add(ranking);
        }
        rankings = ordenarRanking(rankings);
        return rankings;
    }

    public ArrayList<Ranking> getRankingPorNumero() {
        if (rankingsNumero != null && rankingsNumero.size() >0) return rankingsNumero;
        rankingsNumero = new ArrayList<Ranking>();
        int[] list = new int[60];
        ArrayList<Resultado> resultados = getResultados();
        for (Resultado resultado: resultados) {
            Integer dezena1 = resultado.getDezena1();
            Integer dezena2 = resultado.getDezena2();
            Integer dezena3 = resultado.getDezena3();
            Integer dezena4 = resultado.getDezena4();
            Integer dezena5 = resultado.getDezena5();
            Integer dezena6 = resultado.getDezena6();
            list[dezena1-1] +=1;
            list[dezena2-1] +=1;
            list[dezena3-1] +=1;
            list[dezena4-1] +=1;
            list[dezena5-1] +=1;
            list[dezena6-1] +=1;
        }
        for(int i=0; i<60; i++) {
            Ranking ranking = new Ranking();
            ranking.setNumero(i +1);
            ranking.setQuantidade(list[i]);
            rankingsNumero.add(ranking);
        }
        return rankingsNumero;
    }

    public ArrayList<Ranking> getRankingNaoSai() {
        if (rankingsNaoSai != null && rankingsNaoSai.size() >0) return rankingsNaoSai;
        rankingsNaoSai = new ArrayList<Ranking>();
        int[] list = new int[60];
        ArrayList<Resultado> resultados = getResultados();
        for(int i=0; i<60; i++) {
            Integer count = 0;
            for(int j=0; j<resultados.size(); j++) {
                Integer dezena1 = resultados.get(j).getDezena1();
                Integer dezena2 = resultados.get(j).getDezena2();
                Integer dezena3 = resultados.get(j).getDezena3();
                Integer dezena4 = resultados.get(j).getDezena4();
                Integer dezena5 = resultados.get(j).getDezena5();
                Integer dezena6 = resultados.get(j).getDezena6();
                Integer numero = i+1;
                if (numero.equals(dezena1) || numero.equals(dezena2) || numero.equals(dezena3) ||
                        numero.equals(dezena4) || numero.equals(dezena5) || numero.equals(dezena6)) {
                    list[i] = count;
                    break;
                }
                count++;
            }
        }
        for(int i=0; i<60; i++) {
            Ranking ranking = new Ranking();
            ranking.setNumero(i +1);
            ranking.setQuantidade(list[i]);
            rankingsNaoSai.add(ranking);
        }
        rankingsNaoSai = ordenarRanking(rankingsNaoSai);
        return rankingsNaoSai;
    }

    public ArrayList<Ranking> getRankingNaoSaiPorNumero() {
        if (rankingsNaoSaiPorNumero != null && rankingsNaoSaiPorNumero.size() >0) return rankingsNaoSaiPorNumero;
        rankingsNaoSaiPorNumero = new ArrayList<Ranking>();
        int[] list = new int[60];
        ArrayList<Resultado> resultados = getResultados();
        for(int i=0; i<60; i++) {
            Integer count = 0;
            for(int j=0; j<resultados.size(); j++) {
                Integer dezena1 = resultados.get(j).getDezena1();
                Integer dezena2 = resultados.get(j).getDezena2();
                Integer dezena3 = resultados.get(j).getDezena3();
                Integer dezena4 = resultados.get(j).getDezena4();
                Integer dezena5 = resultados.get(j).getDezena5();
                Integer dezena6 = resultados.get(j).getDezena6();
                Integer numero = i+1;
                if (numero.equals(dezena1) || numero.equals(dezena2) || numero.equals(dezena3) ||
                        numero.equals(dezena4) || numero.equals(dezena5) || numero.equals(dezena6)) {
                    list[i] = count;
                    break;
                }
                count++;
            }
        }
        for(int i=0; i<60; i++) {
            Ranking ranking = new Ranking();
            ranking.setNumero(i +1);
            ranking.setQuantidade(list[i]);
            rankingsNaoSaiPorNumero.add(i,ranking);
        }
        return rankingsNaoSaiPorNumero;
    }

    public ArrayList<Acertos> getAcertos(Integer dezena1, Integer dezena2, Integer dezena3, Integer dezena4, Integer dezena5,
                                         Integer dezena6, Integer dezena7, Integer dezena8, Integer dezena9, Integer dezena10,
                                         Integer dezena11, Integer dezena12, Integer dezena13, Integer dezena14, Integer dezena15) {

        ArrayList<Acertos> acertos = new ArrayList<Acertos>();
        Cursor cursor = getAcertosSena(
                dezena1, dezena2, dezena3, dezena4, dezena5,
                dezena6, dezena7, dezena8, dezena9, dezena10,
                dezena11, dezena12, dezena13, dezena14, dezena15);

        if(cursor.moveToFirst()) {
            do{
                Acertos acerto = new Acertos();
                acerto.setAcertos(cursor.getInt(cursor.getColumnIndex("valor")));
                acerto.setConcurso(cursor.getInt(cursor.getColumnIndex("concurso")));
                acertos.add(acerto);
            }while(cursor.moveToNext());
        }

        return acertos;
    }

    private ArrayList<Ranking> ordenarRanking(ArrayList<Ranking> rankins) {
        ArrayList<Ranking> aux = new ArrayList<Ranking>();

        for( int j=0; j<60; j++) {
            Integer numero = 0;
            Integer quantidade = 0;
            for (int i = 0; i < 60; i++) {
                Integer numeroAux = rankins.get(i).getNumero();
                Integer quantAux = rankins.get(i).getQuantidade();
                if (quantAux > quantidade) {
                    if(!aux.contains(rankins.get(i))) {
                        numero = numeroAux;
                        quantidade = quantAux;
                    }
                }
            }
            aux.add(rankins.get(numero - 1));
        }
        return aux;
    }

    public static Boolean isBom(Integer numero) {
        Integer count1 = 1;
        Integer count2 = 1;

        for (Ranking ranking: rankings) {
            if (numero.equals(ranking.getNumero())) {
                for (Ranking ranking2: rankingsNaoSaiPorNumero) {
                    if (numero.equals(ranking2.getNumero()))  {
                        count2 = ranking2.getQuantidade();
                        break;
                    }
                }
                break;
            }
            count1++;
        }

        if (count1 > 30 && count2 > 10) return false;
        return true;
    }

    public void apagarConcurso(String id) {
        db.delete("resultados", "concurso=?", new String[]{id});
    }

    public void inserirRetorno(JSONObject retorno) throws JSONException {
        BancoDeDados bd = new BancoDeDados(context);
        JSONObject concurso =  (JSONObject)retorno.get("concurso");
        JSONObject premiacao = (JSONObject) concurso.get("premiacao");
        JSONObject sena = (JSONObject) premiacao.get("sena");
        JSONObject quina = (JSONObject) premiacao.get("quina");
        JSONObject quadra = (JSONObject) premiacao.get("quadra");
        JSONObject proximo_concurso = (JSONObject) retorno.get("proximo_concurso");

        String numero = (String) concurso.get("numero");
        String data = ((String) concurso.get("data")).replaceAll("/", "-");
        JSONArray jArray  = (JSONArray) concurso.get("dezenas");
        String[] dezenas = {jArray.get(0).toString(), jArray.get(1).toString(), jArray.get(2).toString(),
                jArray.get(3).toString(), jArray.get(4).toString(), jArray.get(5).toString()};
        String ganhador_sena = (String) sena.get("ganhadores");
        String premio_sena = (((String) sena.get("valor_pago")).replace(".", "")).replace(",", ".");
        String ganhador_quina = (String) quina.get("ganhadores");
        String premio_quina = (((String) quina.get("valor_pago")).replace(".", "")).replace(",", ".");
        String ganhador_quadra = (String) quadra.get("ganhadores");
        String premio_quadra = (((String) quadra.get("valor_pago")).replace(".", "")).replace(",", ".");
        String acumulado = (ganhador_sena.equals("0")) ? "1" : "0";
        String proximoConcurso = ((String) proximo_concurso.get("data")).replaceAll("/", "-");
        String valor_estimado = (((String) proximo_concurso.get("valor_estimado")).replace(".", "")).replace(",", ".");
        String valor_acumulado = (((String) concurso.get("valor_acumulado")).replace(".", "")).replace(",", ".");
        String arrecadacao_total = (((String) concurso.get("arrecadacao_total")).replace(".", "")).replace(",", ".");
        String mega_virada_valor_acumulado = (((String) retorno.get("mega_virada_valor_acumulado")).replace(".", "")).replace(",", ".");

        String comando = "INSERT INTO Resultados VALUES (" +
                numero + ", " +
                "'"+data+"', " +
                dezenas[0] +", " +
                dezenas[1] +", " +
                dezenas[2] +", " +
                dezenas[3] +", " +
                dezenas[4] +", " +
                dezenas[5] +", " +
                arrecadacao_total +", " +
                ganhador_sena +", " +
                premio_sena +", " +
                ganhador_quina +", " +
                premio_quina +", " +
                ganhador_quadra +", " +
                premio_quadra +", " +
                acumulado +", " +
                valor_acumulado +", " +
                valor_estimado +", " +
                mega_virada_valor_acumulado +", " +
                "'"+proximoConcurso+"' " + ");";

        bd.abrir();
        bd.executeSQL(comando);
        bd.fechar();
    }

    public void executeSQL(String comando) {
        db.execSQL(comando);
    }

}
