package com.loterias.caixa.megasena.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Constante;
import com.loterias.caixa.megasena.Utils.Utils;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SurpresinhaActivity extends AppCompatActivity {

    private Drawer result;
    private Integer quantidade = 6;
    private TextView lblQuantidade;
    private LinearLayout linear_01;
    private LinearLayout linear_02;
    private LinearLayout linear_03;
    private TextView lblNumerosSorte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surpresinha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ViewUtil viewUtil = new ViewUtil(this);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.teste_logo)
                .withTranslucentStatusBar(false)
                .build();

        //ULTIMO CONCURSO
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().
                withIdentifier(1).withName("Último Concurso").
                withIcon(R.drawable.ic_menu_home);

        //PESQUISAR CONCURSO
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().
                withIdentifier(2).withName("Pesquisar Concurso").
                withIcon(R.drawable.ic_menu_search);

        //MEUS JOGOS
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().
                withIdentifier(3).withName("Meus Jogos").
                withIcon(R.drawable.ic_menu_edit);

        //SURPRESINHA
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().
                withIdentifier(4).withName("Surpresinha").
                withIcon(R.drawable.ic_menu_clover);

        //RANKING
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().
                withIdentifier(5).withName("Ranking").
                withIcon(R.drawable.ic_menu_star);

        //ESTATISTICAS
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().
                withIdentifier(6).withName("Estatísticas").
                withIcon(R.drawable.ic_menu_money);

        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withSelectedItem(4)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
//                        item3,
                        item4,
                        item5,
                        item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        result.closeDrawer();
                        if (drawerItem.getIdentifier() == Constante.MENU_MAIN) {
                            viewUtil.abrirTela(MainActivity.class);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_PESQUISAR) {
                            viewUtil.abrirTela(PesquisarActivity.class);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_RANKING) {
                            viewUtil.abrirTela(RankingActivity.class);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_ESTATISTICA) {
                            viewUtil.abrirTela(EscolherNumerosActivity.class);
                        }
                        return true;
                    }
                })
                .build();


        lblQuantidade = (TextView) findViewById(R.id.lblQuantidade);
        lblQuantidade.setText(quantidade.toString());

        SeekBar seek = (SeekBar) findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                quantidade = 6 + i;
                lblQuantidade.setText(quantidade.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button gerarNumeros = (Button) findViewById(R.id.buttonGerarNumeros);
        gerarNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<Integer> numeros = gerarNumeros(quantidade);
                linear_01 = (LinearLayout) findViewById(R.id.linear_01);
                linear_02 = (LinearLayout) findViewById(R.id.linear_02);
                linear_03 = (LinearLayout) findViewById(R.id.linear_03);
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

                lblNumerosSorte = (TextView) findViewById(R.id.lblNumerosSorte);
                lblNumerosSorte.setVisibility(View.VISIBLE);


                Button analisar = (Button) findViewById(R.id.buttonAnalisar);
                analisar.setVisibility(View.VISIBLE);
                analisar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it = new Intent(SurpresinhaActivity.this, EstatisticasActivity.class);
                        Bundle params = new Bundle();
                        params.putIntegerArrayList("numeros", numeros);
                        it.putExtras(params);
                        startActivity(it);
                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        ViewUtil viewUtil = new ViewUtil(this);
        if(result.isDrawerOpen()){
            result.closeDrawer();
        }
        else{
            viewUtil.abrirTela(MainActivity.class);
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

    private ArrayList<Integer> gerarNumeros(Integer quantidade) {
        Integer[] listaDeNumeros = new Integer[quantidade];
        Random gerador = new Random();
        int i=0;
        while(i<quantidade) {
            Integer valor = gerador.nextInt(61);
            if (valor == 0) continue;
            if (exists(valor, listaDeNumeros)) continue;
            listaDeNumeros[i] = valor;
            i++;
        }
        Arrays.sort(listaDeNumeros);
        ArrayList<Integer> lista = new ArrayList<>();
        Collections.addAll(lista, listaDeNumeros);
        return lista;
    }

    private static boolean exists(Integer value, Integer[] values)  {
        if (values == null) return false;
        if (value == null) return false;
        for (Integer v: values)
            if ((v != null) && v.equals(value)) return true;
        return false;
    }

}
