package com.loterias.caixa.megasena.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loterias.caixa.megasena.Adapter.RankingAdapter;
import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Ranking;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Constante;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    private Drawer result;
    private ArrayList<Ranking> rankings = new ArrayList<Ranking>();
    private RankingAdapter resultadoAdapter;
    public ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
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
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSelectedItem(5)
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

                        } else if (drawerItem.getIdentifier() == Constante.MENU_ESTATISTICA) {
                            viewUtil.abrirTela(EscolherNumerosActivity.class);

                        }
                        return true;
                    }
                })
                .build();

        //
        BancoDeDados bd = new BancoDeDados(this);
        bd.abrir();
        rankings = bd.getRanking();
        bd.fechar();

        list = (ListView) findViewById(R.id.listRanking);
        resultadoAdapter = new RankingAdapter(this, rankings) {

        };

        list.setAdapter(resultadoAdapter);
        list.setDivider(null);

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
