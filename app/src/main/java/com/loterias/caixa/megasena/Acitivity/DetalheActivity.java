package com.loterias.caixa.megasena.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Utils;

public class DetalheActivity extends AppCompatActivity {


    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                Resultado resultado = (Resultado) params.getSerializable("resultado");

                TextView concursoId = (TextView) findViewById(R.id.concursoDetalhes);
                concursoId.setText(resultado.getConcursoId().toString());

                TextView data = (TextView) findViewById(R.id.dataDetalhes);
                data.setText(Utils.formateDate(resultado.getDataSorteio()));

                TextView status = (TextView) findViewById(R.id.statusDetalhes);
                status.setText((resultado.isAcumulado()) ? "Acumulou!" : "Saiu!");

                ImageView dezena01 = (ImageView) findViewById(R.id.dezena01Detalhes);
                dezena01.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena1()));

                ImageView dezena02 = (ImageView) findViewById(R.id.dezena02Detalhes);
                dezena02.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena2()));

                ImageView dezena03 = (ImageView) findViewById(R.id.dezena03Detalhes);
                dezena03.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena3()));

                ImageView dezena04 = (ImageView) findViewById(R.id.dezena04Detalhes);
                dezena04.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena4()));

                ImageView dezena05 = (ImageView) findViewById(R.id.dezena05Detalhes);
                dezena05.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena5()));

                ImageView dezena06 = (ImageView) findViewById(R.id.dezena06Detalhes);
                dezena06.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena6()));

                TextView vlrPremio = (TextView) findViewById(R.id.vlrProxSorteioDetalhes);
                vlrPremio.setText(Utils.formateValor(resultado.getEstimativaPremio()));

                TextView ganhadoresSena = (TextView) findViewById(R.id.ganhadoresSenaDetalhes);
                ganhadoresSena.setText((resultado.getGanhadoresSena() == 0) ? "0" : resultado.getGanhadoresSena() +
                        " (" +Utils.formateValor(resultado.getRateioSena()) + ")");

                TextView ganhadoresQuina = (TextView) findViewById(R.id.ganhadoresQuinaDetalhes);
                ganhadoresQuina.setText((resultado.getGanhadoresQuina() == 0) ? "0" : resultado.getGanhadoresQuina() +
                        " (" +Utils.formateValor(resultado.getRateioQuina()) + ")");

                TextView ganhadoresQuadra = (TextView) findViewById(R.id.ganhadoresQuadraDetalhes);
                ganhadoresQuadra.setText((resultado.getGanhadoresQuadra() == 0) ? "0" : resultado.getGanhadoresQuadra() +
                        " (" +Utils.formateValor(resultado.getRateioQuadra()) + ")");

                TextView vlrAcumulado = (TextView) findViewById(R.id.valorAcumuladoDetalhes);
                vlrAcumulado.setText(Utils.formateValor(resultado.getValorAcumulado()));

                TextView vlrArrecadacaoTotal = (TextView) findViewById(R.id.arrecadacaoTotalDetalhes);
                vlrArrecadacaoTotal.setText(Utils.formateValor(resultado.getArrecadacaoTotal()));

                TextView vlrMegaVirada = (TextView) findViewById(R.id.megaViradaQuadraDetalhes);
                vlrMegaVirada.setText(Utils.formateValor(resultado.getAcumuladoMegaVirada()));

            }
        }

    }

}
