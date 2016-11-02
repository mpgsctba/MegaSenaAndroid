package com.loterias.caixa.megasena.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Acertos;
import com.loterias.caixa.megasena.Model.Ranking;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Utils;

import java.util.ArrayList;

public class EstatisticasActivity extends AppCompatActivity {

    private LinearLayout linear_01;
    private LinearLayout linear_02;
    private LinearLayout linear_03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                ArrayList<Integer> numeros =  params.getIntegerArrayList("numeros");
                linear_01 = (LinearLayout) findViewById(R.id.linear_01_est);
                linear_02 = (LinearLayout) findViewById(R.id.linear_02_est);
                linear_03 = (LinearLayout) findViewById(R.id.linear_03_est);
                limparNumeros();
                if (numeros.size() >= 6) { //linear_01
                    montarLinear01(numeros);
                }
                if (numeros.size() > 6) { //linear_02
                    montarLinear02(numeros);
                }
                if (numeros.size() >= 13) { //linear _03
                    montarLinear03(numeros);
                }

                Integer[] array = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

                for (int i=0; i<numeros.size(); i++) {
                    array[i] = numeros.get(i);
                }

                BancoDeDados bd = new BancoDeDados(this);
                bd.abrir();
                ArrayList<Acertos> acertos = bd.getAcertos(
                        array[0], array[1], array[2], array[3], array[4],
                        array[5], array[6], array[7], array[8], array[9],
                        array[10], array[11], array[12], array[13], array[14]);

                ArrayList<Ranking> listaRanking = bd.getRanking();
                ArrayList<Ranking> listaRankingPorNumero = bd.getRankingPorNumero();
                //ArrayList<Ranking> listaRankingNaoSai = bd.getRankingNaoSai();
                ArrayList<Ranking> listaRankingNaoSaiPorNumero = bd.getRankingNaoSaiPorNumero();

                bd.fechar();

                Integer countSena = 0;
                Integer countQuina = 0;
                Integer countQuadra = 0;

                for (Acertos acerto:acertos) {
                    if (acerto.getAcertos() == 6) {
                        countSena++;
                    } else if (acerto.getAcertos() == 5) {
                        countQuina++;
                    } else if (acerto.getAcertos() == 4) {
                        countQuadra++;
                    }
                }

                TextView tvSena = (TextView) findViewById(R.id.quantidadeSena);
                tvSena.setText(countSena + "x");

                TextView tvQuina = (TextView) findViewById(R.id.quantidadeQuina);
                tvQuina.setText(countQuina + "x");

                TextView tvQuadra = (TextView) findViewById(R.id.quantidadeQuadra);
                tvQuadra.setText(countQuadra + "x");

                ImageView image01 = (ImageView) findViewById(R.id.dezena01Estatistica);
                image01.setImageResource(Utils.findImagemIdByNumero(array[0]));
                TextView tvSaiu01 = (TextView) findViewById(R.id.quantidadeSaiu1);
                tvSaiu01.setText(listaRankingPorNumero.get(array[0]-1).getQuantidade() + "x");
                ((TextView) findViewById(R.id.quantidadeQueNaoSai1)).setText(listaRankingNaoSaiPorNumero.get(array[0] - 1).getQuantidade() + "x");
                if (!BancoDeDados.isBom(array[0])) ((ImageView) findViewById(R.id.isBom1)).setImageResource(R.drawable.not_like);

                ImageView image02 = (ImageView) findViewById(R.id.dezena02Estatistica);
                image02.setImageResource(Utils.findImagemIdByNumero(array[1]));
                TextView tvSaiu02 = (TextView) findViewById(R.id.quantidadeSaiu2);
                tvSaiu02.setText(listaRankingPorNumero.get(array[1]-1).getQuantidade() + "x");
                ((TextView) findViewById(R.id.quantidadeQueNaoSai2)).setText(listaRankingNaoSaiPorNumero.get(array[1] - 1).getQuantidade() + "x");
                if (!BancoDeDados.isBom(array[1])) ((ImageView) findViewById(R.id.isBom2)).setImageResource(R.drawable.not_like);

                ImageView image03 = (ImageView) findViewById(R.id.dezena03Estatistica);
                image03.setImageResource(Utils.findImagemIdByNumero(array[2]));
                TextView tvSaiu03 = (TextView) findViewById(R.id.quantidadeSaiu3);
                tvSaiu03.setText(listaRankingPorNumero.get(array[2]-1).getQuantidade() + "x");
                ((TextView) findViewById(R.id.quantidadeQueNaoSai3)).setText(listaRankingNaoSaiPorNumero.get(array[2] - 1).getQuantidade() + "x");
                if (!BancoDeDados.isBom(array[2])) ((ImageView) findViewById(R.id.isBom3)).setImageResource(R.drawable.not_like);

                ImageView image04 = (ImageView) findViewById(R.id.dezena04Estatistica);
                image04.setImageResource(Utils.findImagemIdByNumero(array[3]));
                TextView tvSaiu04 = (TextView) findViewById(R.id.quantidadeSaiu4);
                tvSaiu04.setText(listaRankingPorNumero.get(array[3]-1).getQuantidade() + "x");
                ((TextView) findViewById(R.id.quantidadeQueNaoSai4)).setText(listaRankingNaoSaiPorNumero.get(array[3] - 1).getQuantidade() + "x");
                if (!BancoDeDados.isBom(array[3])) ((ImageView) findViewById(R.id.isBom4)).setImageResource(R.drawable.not_like);

                ImageView image05 = (ImageView) findViewById(R.id.dezena05Estatistica);
                image05.setImageResource(Utils.findImagemIdByNumero(array[4]));
                TextView tvSaiu05 = (TextView) findViewById(R.id.quantidadeSaiu5);
                tvSaiu05.setText(listaRankingPorNumero.get(array[4]-1).getQuantidade() + "x");
                ((TextView) findViewById(R.id.quantidadeQueNaoSai5)).setText(listaRankingNaoSaiPorNumero.get(array[4] - 1).getQuantidade() + "x");
                if (!BancoDeDados.isBom(array[4])) ((ImageView) findViewById(R.id.isBom5)).setImageResource(R.drawable.not_like);

                ImageView image06 = (ImageView) findViewById(R.id.dezena06Estatistica);
                image06.setImageResource(Utils.findImagemIdByNumero(array[5]));
                TextView tvSaiu06 = (TextView) findViewById(R.id.quantidadeSaiu6);
                tvSaiu06.setText(listaRankingPorNumero.get(array[5]-1).getQuantidade()+"x");
                ((TextView) findViewById(R.id.quantidadeQueNaoSai6)).setText(listaRankingNaoSaiPorNumero.get(array[5] - 1).getQuantidade() + "x");
                if (!BancoDeDados.isBom(array[5])) ((ImageView) findViewById(R.id.isBom6)).setImageResource(R.drawable.not_like);

                LinearLayout layoutPadrao = (LinearLayout) findViewById(R.id.layout_01);

                //numero 7
                if (array[6] != null && array[6] != 0) {
                    ((ImageView) findViewById(R.id.dezena07Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[6]));
                    ((TextView) findViewById(R.id.quantidadeSaiu7)).setText(listaRankingPorNumero.get(array[6] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_07)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai7)).setText(listaRankingNaoSaiPorNumero.get(array[6] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[6])) ((ImageView) findViewById(R.id.isBom7)).setImageResource(R.drawable.not_like);
                }
                //numero 8
                if (array[7] != null && array[7] != 0) {
                    ((ImageView) findViewById(R.id.dezena08Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[7]));
                    ((TextView) findViewById(R.id.quantidadeSaiu8)).setText(listaRankingPorNumero.get(array[7] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_08)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai8)).setText(listaRankingNaoSaiPorNumero.get(array[7] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[7])) ((ImageView) findViewById(R.id.isBom8)).setImageResource(R.drawable.not_like);
                }
                //numero 9
                if (array[8] != null && array[8] != 0) {
                    ((ImageView) findViewById(R.id.dezena09Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[8]));
                    ((TextView) findViewById(R.id.quantidadeSaiu9)).setText(listaRankingPorNumero.get(array[8] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_09)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai9)).setText(listaRankingNaoSaiPorNumero.get(array[8] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[8])) ((ImageView) findViewById(R.id.isBom9)).setImageResource(R.drawable.not_like);
                }
                //numero 10
                if (array[9] != null && array[9] != 0) {
                    ((ImageView) findViewById(R.id.dezena10Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[9]));
                    ((TextView) findViewById(R.id.quantidadeSaiu10)).setText(listaRankingPorNumero.get(array[9] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_10)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai10)).setText(listaRankingNaoSaiPorNumero.get(array[9] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[9])) ((ImageView) findViewById(R.id.isBom10)).setImageResource(R.drawable.not_like);
                }
                //numero 11
                if (array[10] != null && array[10] != 0) {
                    ((ImageView) findViewById(R.id.dezena11Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[10]));
                    ((TextView) findViewById(R.id.quantidadeSaiu11)).setText(listaRankingPorNumero.get(array[10] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_11)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai11)).setText(listaRankingNaoSaiPorNumero.get(array[10] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[10])) ((ImageView) findViewById(R.id.isBom11)).setImageResource(R.drawable.not_like);
                }
                //numero 12
                if (array[11] != null && array[11] != 0) {
                    ((ImageView) findViewById(R.id.dezena12Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[11]));
                    ((TextView) findViewById(R.id.quantidadeSaiu12)).setText(listaRankingPorNumero.get(array[11] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_12)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai12)).setText(listaRankingNaoSaiPorNumero.get(array[11] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[11])) ((ImageView) findViewById(R.id.isBom12)).setImageResource(R.drawable.not_like);
                }
                //numero 13
                if (array[12] != null && array[12] != 0) {
                    ((ImageView) findViewById(R.id.dezena13Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[12]));
                    ((TextView) findViewById(R.id.quantidadeSaiu13)).setText(listaRankingPorNumero.get(array[12] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_13)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai13)).setText(listaRankingNaoSaiPorNumero.get(array[12] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[12])) ((ImageView) findViewById(R.id.isBom13)).setImageResource(R.drawable.not_like);
                }
                //numero 14
                if (array[13] != null && array[13] != 0) {
                    ((ImageView) findViewById(R.id.dezena14Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[13]));
                    ((TextView) findViewById(R.id.quantidadeSaiu14)).setText(listaRankingPorNumero.get(array[13] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_14)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai14)).setText(listaRankingNaoSaiPorNumero.get(array[13] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[13])) ((ImageView) findViewById(R.id.isBom14)).setImageResource(R.drawable.not_like);
                }
                //numero 15
                if (array[14] != null && array[14] != 0) {
                    ((ImageView) findViewById(R.id.dezena15Estatistica)).setImageResource(Utils.findImagemIdByNumero(array[14]));
                    ((TextView) findViewById(R.id.quantidadeSaiu15)).setText(listaRankingPorNumero.get(array[14] - 1).getQuantidade() + "x");
                    ((LinearLayout) findViewById(R.id.layout_15)).setLayoutParams(layoutPadrao.getLayoutParams());
                    ((TextView) findViewById(R.id.quantidadeQueNaoSai15)).setText(listaRankingNaoSaiPorNumero.get(array[14] - 1).getQuantidade() + "x");
                    if (!BancoDeDados.isBom(array[14])) ((ImageView) findViewById(R.id.isBom15)).setImageResource(R.drawable.not_like);
                }

            }
        }

    }

    private void montarLinear01(ArrayList<Integer> numeros) {
        linear_02.getLayoutParams().height = 0;
        linear_03.getLayoutParams().height = 0;
        for (int i=0; i<6; i++) {
            ImageView image = new ImageView(getApplicationContext());
            image.setImageResource(Utils.findImagemIdByNumero(numeros.get(i)));
            image.setId(i);
            image.setAdjustViewBounds(true);
            linear_01.addView(image);
        }
    }

    private void montarLinear02(ArrayList<Integer> numeros) {
        linear_02.getLayoutParams().height = linear_01.getLayoutParams().height;
        for (int i=6; i<numeros.size(); i++) {
            if (i >11) continue;
            ImageView image = new ImageView(getApplicationContext());
            image.setImageResource(Utils.findImagemIdByNumero(numeros.get(i)));
            image.setAdjustViewBounds(true);
            linear_02.addView(image);
        }
    }

    private void montarLinear03(ArrayList<Integer> numeros) {
        linear_03.getLayoutParams().height = linear_01.getLayoutParams().height;
        for (int i=12; i<numeros.size(); i++) {
            ImageView image = new ImageView(getApplicationContext());
            image.setImageResource(Utils.findImagemIdByNumero(numeros.get(i)));
            image.setAdjustViewBounds(true);
            linear_03.addView(image);
        }
    }

    private void limparNumeros() {
        linear_01.removeAllViews();
        linear_02.removeAllViews();
        linear_03.removeAllViews();
    }

}
