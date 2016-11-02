package com.loterias.caixa.megasena.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.loterias.caixa.megasena.Utils.Constante;

/**
 * Created by Marcos on 19/10/2015.
 */
public class MeuOpenHelper extends SQLiteOpenHelper {

    String SQL_CREATE_TABLE = "create table Resultados " +
            "(concurso integer, " +
            "data date, " +
            "dezena1 integer, " +
            "dezena2 integer, " +
            "dezena3 integer, " +
            "dezena4 integer, " +
            "dezena5 integer, " +
            "dezena6 integer, " +
            "arrecadacaoTotal DECIMAL(11,2), " +
            "ganhadoresSena INTEGER, " +
            "rateioSena DECIMAL(11,2), " +
            "ganhadoresQuina INTEGER, " +
            "rateioQuina DECIMAL(11,2), " +
            "ganhadoresQuadra INTEGER, " +
            "rateioQuadra DECIMAL(11,2), " +
            "acumulado BIT, " +
            "valorAcumulado DECIMAL(11,2), " +
            "estimativaPremio DECIMAL(11,2), " +
            "acumuladoMegaVirada DECIMAL(11,2), " +
            "proximoSorteio date " +
            "); ";

    MeuOpenHelper (Context context){
        super(context, "db_MegaSena", null, 4); // 30/10/2016
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        String insert[] = Constante.getSqlInsert().toString().split(";");
        for (String valor:insert) {
            if (valor.length()>0)
                db.execSQL(valor);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Resultados");
        onCreate(db);
    }
}
