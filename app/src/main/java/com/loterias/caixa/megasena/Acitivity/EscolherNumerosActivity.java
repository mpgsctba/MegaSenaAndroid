package com.loterias.caixa.megasena.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Constante;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class EscolherNumerosActivity extends AppCompatActivity {

    private Drawer result;
    private Boolean[] selecionados = {false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,false,false};


    ImageButton numero_01;
    ImageButton numero_02;
    ImageButton numero_03;
    ImageButton numero_04;
    ImageButton numero_05;
    ImageButton numero_06;
    ImageButton numero_07;
    ImageButton numero_08;
    ImageButton numero_09;
    ImageButton numero_10;
    ImageButton numero_11;
    ImageButton numero_12;
    ImageButton numero_13;
    ImageButton numero_14;
    ImageButton numero_15;
    ImageButton numero_16;
    ImageButton numero_17;
    ImageButton numero_18;
    ImageButton numero_19;
    ImageButton numero_20;
    ImageButton numero_21;
    ImageButton numero_22;
    ImageButton numero_23;
    ImageButton numero_24;
    ImageButton numero_25;
    ImageButton numero_26;
    ImageButton numero_27;
    ImageButton numero_28;
    ImageButton numero_29;
    ImageButton numero_30;
    ImageButton numero_31;
    ImageButton numero_32;
    ImageButton numero_33;
    ImageButton numero_34;
    ImageButton numero_35;
    ImageButton numero_36;
    ImageButton numero_37;
    ImageButton numero_38;
    ImageButton numero_39;
    ImageButton numero_40;
    ImageButton numero_41;
    ImageButton numero_42;
    ImageButton numero_43;
    ImageButton numero_44;
    ImageButton numero_45;
    ImageButton numero_46;
    ImageButton numero_47;
    ImageButton numero_48;
    ImageButton numero_49;
    ImageButton numero_50;
    ImageButton numero_51;
    ImageButton numero_52;
    ImageButton numero_53;
    ImageButton numero_54;
    ImageButton numero_55;
    ImageButton numero_56;
    ImageButton numero_57;
    ImageButton numero_58;
    ImageButton numero_59;
    ImageButton numero_60;
    TextView escolhidos;
    Button buttonAnalisarEstatisticas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_numeros);
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
                .withSelectedItem(6)
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
                        } else if (drawerItem.getIdentifier() == Constante.MENU_MEUSJOGOS) {

                        } else if (drawerItem.getIdentifier() == Constante.MENU_SURPRESINHA) {
                            viewUtil.abrirTela(SurpresinhaActivity.class);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_RANKING) {
                            viewUtil.abrirTela(RankingActivity.class);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_ESTATISTICA) {

                        }
                        return true;
                    }
                })
                .build();


        escolhidos = (TextView) findViewById(R.id.escolhidos);

        numero_01 = (ImageButton) findViewById(R.id.dezena01);
        numero_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[0]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[0] = true;
                    numero_01.setBackground(getResources().getDrawable(R.drawable.numero_01_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[0] = false;
                    numero_01.setBackground(getResources().getDrawable(R.drawable.numero_01));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_02 = (ImageButton) findViewById(R.id.dezena02);
        numero_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[1]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[1] = true;
                    numero_02.setBackground(getResources().getDrawable(R.drawable.numero_02_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[1] = false;
                    numero_02.setBackground(getResources().getDrawable(R.drawable.numero_02));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_03 = (ImageButton) findViewById(R.id.dezena03);
        numero_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[2]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[2] = true;
                    numero_03.setBackground(getResources().getDrawable(R.drawable.numero_03_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[2] = false;
                    numero_03.setBackground(getResources().getDrawable(R.drawable.numero_03));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_04 = (ImageButton) findViewById(R.id.dezena04);
        numero_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[3]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[3] = true;
                    numero_04.setBackground(getResources().getDrawable(R.drawable.numero_04_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[3] = false;
                    numero_04.setBackground(getResources().getDrawable(R.drawable.numero_04));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_05 = (ImageButton) findViewById(R.id.dezena05);
        numero_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[4]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[4] = true;
                    numero_05.setBackground(getResources().getDrawable(R.drawable.numero_05_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[4] = false;
                    numero_05.setBackground(getResources().getDrawable(R.drawable.numero_05));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_06 = (ImageButton) findViewById(R.id.dezena06);
        numero_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[5]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[5] = true;
                    numero_06.setBackground(getResources().getDrawable(R.drawable.numero_06_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[5] = false;
                    numero_06.setBackground(getResources().getDrawable(R.drawable.numero_06));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_07 = (ImageButton) findViewById(R.id.dezena07);
        numero_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[6]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[6] = true;
                    numero_07.setBackground(getResources().getDrawable(R.drawable.numero_07_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[6] = false;
                    numero_07.setBackground(getResources().getDrawable(R.drawable.numero_07));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_08 = (ImageButton) findViewById(R.id.dezena08);
        numero_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[7]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[7] = true;
                    numero_08.setBackground(getResources().getDrawable(R.drawable.numero_08_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[7] = false;
                    numero_08.setBackground(getResources().getDrawable(R.drawable.numero_08));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_09 = (ImageButton) findViewById(R.id.dezena09);
        numero_09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[8]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[8] = true;
                    numero_09.setBackground(getResources().getDrawable(R.drawable.numero_09_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[8] = false;
                    numero_09.setBackground(getResources().getDrawable(R.drawable.numero_09));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_10 = (ImageButton) findViewById(R.id.dezena10);
        numero_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[9]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[9] = true;
                    numero_10.setBackground(getResources().getDrawable(R.drawable.numero_10_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[9] = false;
                    numero_10.setBackground(getResources().getDrawable(R.drawable.numero_10));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_11 = (ImageButton) findViewById(R.id.dezena11);
        numero_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[10]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[10] = true;
                    numero_11.setBackground(getResources().getDrawable(R.drawable.numero_11_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[10] = false;
                    numero_11.setBackground(getResources().getDrawable(R.drawable.numero_11));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_12 = (ImageButton) findViewById(R.id.dezena12);
        numero_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[11]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[11] = true;
                    numero_12.setBackground(getResources().getDrawable(R.drawable.numero_12_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[11] = false;
                    numero_12.setBackground(getResources().getDrawable(R.drawable.numero_12));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_13 = (ImageButton) findViewById(R.id.dezena13);
        numero_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[12]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[12] = true;
                    numero_13.setBackground(getResources().getDrawable(R.drawable.numero_13_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[12] = false;
                    numero_13.setBackground(getResources().getDrawable(R.drawable.numero_13));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_14 = (ImageButton) findViewById(R.id.dezena14);
        numero_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[13]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[13] = true;
                    numero_14.setBackground(getResources().getDrawable(R.drawable.numero_14_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[13] = false;
                    numero_14.setBackground(getResources().getDrawable(R.drawable.numero_14));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_15 = (ImageButton) findViewById(R.id.dezena15);
        numero_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[14]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[14] = true;
                    numero_15.setBackground(getResources().getDrawable(R.drawable.numero_15_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[14] = false;
                    numero_15.setBackground(getResources().getDrawable(R.drawable.numero_15));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_16 = (ImageButton) findViewById(R.id.dezena16);
        numero_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[15]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[15] = true;
                    numero_16.setBackground(getResources().getDrawable(R.drawable.numero_16_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[15] = false;
                    numero_16.setBackground(getResources().getDrawable(R.drawable.numero_16));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_17 = (ImageButton) findViewById(R.id.dezena17);
        numero_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[16]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[16] = true;
                    numero_17.setBackground(getResources().getDrawable(R.drawable.numero_17_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[16] = false;
                    numero_17.setBackground(getResources().getDrawable(R.drawable.numero_17));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_18 = (ImageButton) findViewById(R.id.dezena18);
        numero_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[17]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[17] = true;
                    numero_18.setBackground(getResources().getDrawable(R.drawable.numero_18_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[17] = false;
                    numero_18.setBackground(getResources().getDrawable(R.drawable.numero_18));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_19 = (ImageButton) findViewById(R.id.dezena19);
        numero_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[18]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[18] = true;
                    numero_19.setBackground(getResources().getDrawable(R.drawable.numero_19_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[18] = false;
                    numero_19.setBackground(getResources().getDrawable(R.drawable.numero_19));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_20 = (ImageButton) findViewById(R.id.dezena20);
        numero_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[19]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[19] = true;
                    numero_20.setBackground(getResources().getDrawable(R.drawable.numero_20_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[19] = false;
                    numero_20.setBackground(getResources().getDrawable(R.drawable.numero_20));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_21 = (ImageButton) findViewById(R.id.dezena21);
        numero_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[20]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[20] = true;
                    numero_21.setBackground(getResources().getDrawable(R.drawable.numero_21_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[20] = false;
                    numero_21.setBackground(getResources().getDrawable(R.drawable.numero_21));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_22 = (ImageButton) findViewById(R.id.dezena22);
        numero_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[21]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[21] = true;
                    numero_22.setBackground(getResources().getDrawable(R.drawable.numero_22_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[21] = false;
                    numero_22.setBackground(getResources().getDrawable(R.drawable.numero_22));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_23 = (ImageButton) findViewById(R.id.dezena23);
        numero_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[22]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[22] = true;
                    numero_23.setBackground(getResources().getDrawable(R.drawable.numero_23_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[22] = false;
                    numero_23.setBackground(getResources().getDrawable(R.drawable.numero_23));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_24 = (ImageButton) findViewById(R.id.dezena24);
        numero_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[23]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[23] = true;
                    numero_24.setBackground(getResources().getDrawable(R.drawable.numero_24_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[23] = false;
                    numero_24.setBackground(getResources().getDrawable(R.drawable.numero_24));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_25 = (ImageButton) findViewById(R.id.dezena25);
        numero_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[24]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[24] = true;
                    numero_25.setBackground(getResources().getDrawable(R.drawable.numero_25_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[24] = false;
                    numero_25.setBackground(getResources().getDrawable(R.drawable.numero_25));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_26 = (ImageButton) findViewById(R.id.dezena26);
        numero_26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[25]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[25] = true;
                    numero_26.setBackground(getResources().getDrawable(R.drawable.numero_26_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[25] = false;
                    numero_26.setBackground(getResources().getDrawable(R.drawable.numero_26));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_27 = (ImageButton) findViewById(R.id.dezena27);
        numero_27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[26]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[26] = true;
                    numero_27.setBackground(getResources().getDrawable(R.drawable.numero_27_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[26] = false;
                    numero_27.setBackground(getResources().getDrawable(R.drawable.numero_27));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_28 = (ImageButton) findViewById(R.id.dezena28);
        numero_28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[27]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[27] = true;
                    numero_28.setBackground(getResources().getDrawable(R.drawable.numero_28_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[27] = false;
                    numero_28.setBackground(getResources().getDrawable(R.drawable.numero_28));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_29 = (ImageButton) findViewById(R.id.dezena29);
        numero_29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[28]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[28] = true;
                    numero_29.setBackground(getResources().getDrawable(R.drawable.numero_29_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[28] = false;
                    numero_29.setBackground(getResources().getDrawable(R.drawable.numero_29));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_30 = (ImageButton) findViewById(R.id.dezena30);
        numero_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[29]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[29] = true;
                    numero_30.setBackground(getResources().getDrawable(R.drawable.numero_30_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[29] = false;
                    numero_30.setBackground(getResources().getDrawable(R.drawable.numero_30));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_31 = (ImageButton) findViewById(R.id.dezena31);
        numero_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[30]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[30] = true;
                    numero_31.setBackground(getResources().getDrawable(R.drawable.numero_31_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[30] = false;
                    numero_31.setBackground(getResources().getDrawable(R.drawable.numero_31));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_32 = (ImageButton) findViewById(R.id.dezena32);
        numero_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[31]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[31] = true;
                    numero_32.setBackground(getResources().getDrawable(R.drawable.numero_32_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[31] = false;
                    numero_32.setBackground(getResources().getDrawable(R.drawable.numero_32));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_33 = (ImageButton) findViewById(R.id.dezena33);
        numero_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[32]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[32] = true;
                    numero_33.setBackground(getResources().getDrawable(R.drawable.numero_33_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[32] = false;
                    numero_33.setBackground(getResources().getDrawable(R.drawable.numero_33));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_34 = (ImageButton) findViewById(R.id.dezena34);
        numero_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[33]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[33] = true;
                    numero_34.setBackground(getResources().getDrawable(R.drawable.numero_34_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[33] = false;
                    numero_34.setBackground(getResources().getDrawable(R.drawable.numero_34));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_35 = (ImageButton) findViewById(R.id.dezena35);
        numero_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[34]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[34] = true;
                    numero_35.setBackground(getResources().getDrawable(R.drawable.numero_35_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[34] = false;
                    numero_35.setBackground(getResources().getDrawable(R.drawable.numero_35));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_36 = (ImageButton) findViewById(R.id.dezena36);
        numero_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[35]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[35] = true;
                    numero_36.setBackground(getResources().getDrawable(R.drawable.numero_36_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[35] = false;
                    numero_36.setBackground(getResources().getDrawable(R.drawable.numero_36));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_37 = (ImageButton) findViewById(R.id.dezena37);
        numero_37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[36]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[36] = true;
                    numero_37.setBackground(getResources().getDrawable(R.drawable.numero_37_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[36] = false;
                    numero_37.setBackground(getResources().getDrawable(R.drawable.numero_37));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_38 = (ImageButton) findViewById(R.id.dezena38);
        numero_38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[37]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[37] = true;
                    numero_38.setBackground(getResources().getDrawable(R.drawable.numero_38_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[37] = false;
                    numero_38.setBackground(getResources().getDrawable(R.drawable.numero_38));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_39 = (ImageButton) findViewById(R.id.dezena39);
        numero_39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[38]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[38] = true;
                    numero_39.setBackground(getResources().getDrawable(R.drawable.numero_39_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[38] = false;
                    numero_39.setBackground(getResources().getDrawable(R.drawable.numero_39));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_40 = (ImageButton) findViewById(R.id.dezena40);
        numero_40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[39]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[39] = true;
                    numero_40.setBackground(getResources().getDrawable(R.drawable.numero_40_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[39] = false;
                    numero_40.setBackground(getResources().getDrawable(R.drawable.numero_40));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_41 = (ImageButton) findViewById(R.id.dezena41);
        numero_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[40]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[40] = true;
                    numero_41.setBackground(getResources().getDrawable(R.drawable.numero_41_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[40] = false;
                    numero_41.setBackground(getResources().getDrawable(R.drawable.numero_41));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_42 = (ImageButton) findViewById(R.id.dezena42);
        numero_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[41]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[41] = true;
                    numero_42.setBackground(getResources().getDrawable(R.drawable.numero_42_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[41] = false;
                    numero_42.setBackground(getResources().getDrawable(R.drawable.numero_42));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_43 = (ImageButton) findViewById(R.id.dezena43);
        numero_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[42]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[42] = true;
                    numero_43.setBackground(getResources().getDrawable(R.drawable.numero_43_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[42] = false;
                    numero_43.setBackground(getResources().getDrawable(R.drawable.numero_43));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_44 = (ImageButton) findViewById(R.id.dezena44);
        numero_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[43]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[43] = true;
                    numero_44.setBackground(getResources().getDrawable(R.drawable.numero_44_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[43] = false;
                    numero_44.setBackground(getResources().getDrawable(R.drawable.numero_44));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_45 = (ImageButton) findViewById(R.id.dezena45);
        numero_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[44]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[44] = true;
                    numero_45.setBackground(getResources().getDrawable(R.drawable.numero_45_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[44] = false;
                    numero_45.setBackground(getResources().getDrawable(R.drawable.numero_45));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_46 = (ImageButton) findViewById(R.id.dezena46);
        numero_46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[45]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[45] = true;
                    numero_46.setBackground(getResources().getDrawable(R.drawable.numero_46_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[45] = false;
                    numero_46.setBackground(getResources().getDrawable(R.drawable.numero_46));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_47 = (ImageButton) findViewById(R.id.dezena47);
        numero_47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[46]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[46] = true;
                    numero_47.setBackground(getResources().getDrawable(R.drawable.numero_47_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[46] = false;
                    numero_47.setBackground(getResources().getDrawable(R.drawable.numero_47));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_48 = (ImageButton) findViewById(R.id.dezena48);
        numero_48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[47]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[47] = true;
                    numero_48.setBackground(getResources().getDrawable(R.drawable.numero_48_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[47] = false;
                    numero_48.setBackground(getResources().getDrawable(R.drawable.numero_48));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_49 = (ImageButton) findViewById(R.id.dezena49);
        numero_49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[48]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[48] = true;
                    numero_49.setBackground(getResources().getDrawable(R.drawable.numero_49_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[48] = false;
                    numero_49.setBackground(getResources().getDrawable(R.drawable.numero_49));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_50 = (ImageButton) findViewById(R.id.dezena50);
        numero_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[49]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[49] = true;
                    numero_50.setBackground(getResources().getDrawable(R.drawable.numero_50_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[49] = false;
                    numero_50.setBackground(getResources().getDrawable(R.drawable.numero_50));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_51 = (ImageButton) findViewById(R.id.dezena51);
        numero_51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[50]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[50] = true;
                    numero_51.setBackground(getResources().getDrawable(R.drawable.numero_51_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[50] = false;
                    numero_51.setBackground(getResources().getDrawable(R.drawable.numero_51));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_52 = (ImageButton) findViewById(R.id.dezena52);
        numero_52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[51]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[51] = true;
                    numero_52.setBackground(getResources().getDrawable(R.drawable.numero_52_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[51] = false;
                    numero_52.setBackground(getResources().getDrawable(R.drawable.numero_52));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_53 = (ImageButton) findViewById(R.id.dezena53);
        numero_53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[52]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[52] = true;
                    numero_53.setBackground(getResources().getDrawable(R.drawable.numero_53_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[52] = false;
                    numero_53.setBackground(getResources().getDrawable(R.drawable.numero_53));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_54 = (ImageButton) findViewById(R.id.dezena54);
        numero_54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[53]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[53] = true;
                    numero_54.setBackground(getResources().getDrawable(R.drawable.numero_54_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[53] = false;
                    numero_54.setBackground(getResources().getDrawable(R.drawable.numero_54));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_55 = (ImageButton) findViewById(R.id.dezena55);
        numero_55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[54]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[54] = true;
                    numero_55.setBackground(getResources().getDrawable(R.drawable.numero_55_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[54] = false;
                    numero_55.setBackground(getResources().getDrawable(R.drawable.numero_55));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_56 = (ImageButton) findViewById(R.id.dezena56);
        numero_56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[55]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[55] = true;
                    numero_56.setBackground(getResources().getDrawable(R.drawable.numero_56_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[55] = false;
                    numero_56.setBackground(getResources().getDrawable(R.drawable.numero_56));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_57 = (ImageButton) findViewById(R.id.dezena57);
        numero_57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[56]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[56] = true;
                    numero_57.setBackground(getResources().getDrawable(R.drawable.numero_57_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[56] = false;
                    numero_57.setBackground(getResources().getDrawable(R.drawable.numero_57));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_58 = (ImageButton) findViewById(R.id.dezena58);
        numero_58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[57]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[57] = true;
                    numero_58.setBackground(getResources().getDrawable(R.drawable.numero_58_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[57] = false;
                    numero_58.setBackground(getResources().getDrawable(R.drawable.numero_58));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_59 = (ImageButton) findViewById(R.id.dezena59);
        numero_59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[58]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[58] = true;
                    numero_59.setBackground(getResources().getDrawable(R.drawable.numero_59_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[58] = false;
                    numero_59.setBackground(getResources().getDrawable(R.drawable.numero_59));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        numero_60 = (ImageButton) findViewById(R.id.dezena60);
        numero_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selecionados[59]) {
                    if (getQuantSelecionados(selecionados) >= 15) return;
                    selecionados[59] = true;
                    numero_60.setBackground(getResources().getDrawable(R.drawable.numero_60_amarelo));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                } else {
                    selecionados[59] = false;
                    numero_60.setBackground(getResources().getDrawable(R.drawable.numero_60));
                    escolhidos.setText("Escolhidos: " + getQuantSelecionados(selecionados));
                }
            }
        });

        buttonAnalisarEstatisticas = (Button) findViewById(R.id.buttonAnalisarEstatisticas);
        buttonAnalisarEstatisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer quantidadeSelecionado = getQuantSelecionados(selecionados);
                if (quantidadeSelecionado < 6) {
                    Toast.makeText(getApplicationContext(), "Escolha no minimo 6 numeros!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Integer> numeros = new ArrayList<Integer>();
                for (int i=0; i<60; i++) {
                    if (selecionados[i]) {
                        numeros.add(i+1);
                    }
                }
                Intent it = new Intent(EscolherNumerosActivity.this, EstatisticasActivity.class);
                Bundle params = new Bundle();
                params.putIntegerArrayList("numeros", numeros);
                it.putExtras(params);
                startActivity(it);
            }
        });

    }

    private Integer getQuantSelecionados(Boolean[] selecionados) {
        Integer count = 0;
        for (Boolean item : selecionados) {
            if (item) count++;
        }

        return count;
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
}
