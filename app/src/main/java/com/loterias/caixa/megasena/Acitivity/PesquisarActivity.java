package com.loterias.caixa.megasena.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loterias.caixa.megasena.Adapter.ResultadoAdapter;
import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Fragment.ResultadoFragment;
import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Constante;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class PesquisarActivity extends AppCompatActivity {

    private Drawer result;
    private ImageButton pesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                .withSelectedItem(Constante.MENU_PESQUISAR)
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
                            voltarMenuPrincipal();
                        } else if (drawerItem.getIdentifier() == Constante.MENU_PESQUISAR) {

                        } else if (drawerItem.getIdentifier() == Constante.MENU_MEUSJOGOS) {

                        } else if (drawerItem.getIdentifier() == Constante.MENU_SURPRESINHA) {
                            Intent intent = new Intent(PesquisarActivity.this, SurpresinhaActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_RANKING) {
                            Intent intent = new Intent(PesquisarActivity.this, RankingActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_ESTATISTICA) {
                            Intent intent = new Intent(PesquisarActivity.this, EscolherNumerosActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();

        //FRAGMENT
        ResultadoFragment fragment = (ResultadoFragment) getSupportFragmentManager().findFragmentByTag("pesqFragment");
        if (fragment == null) {
            fragment = new ResultadoFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_resultado, fragment, "pesqFragment");
            ft.commit();
        }

        final EditText concurso = (EditText) findViewById(R.id.concursoSelecionado);
        concurso.setFocusableInTouchMode(false);
        concurso.setFocusable(false);
        concurso.setFocusableInTouchMode(true);
        concurso.setFocusable(true);

        pesquisar = (ImageButton) findViewById(R.id.pesquisar);
        final ResultadoFragment finalFragment = fragment;
        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (concurso.getText().length() == 0) {
                    Snackbar.make(view, "Preencha o campo concurso!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
//                    Toast.makeText(getApplication(), "Preencha o campo concurso!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer concursoId = Integer.parseInt(concurso.getText().toString());
                BancoDeDados bd = new BancoDeDados(getApplication());
                bd.abrir();
                Resultado resultado = bd.getResultado(concursoId);
                bd.fechar();
                if (resultado == null || resultado.getConcursoId() == null) {
                    Snackbar.make(view, "Nenhum resultado encontrado!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
//                    Toast.makeText(getApplication(), "Nenhum resultado encontrado!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Resultado> resultados = new ArrayList<Resultado>();
                resultados.add(resultado);
                ResultadoAdapter adapter = new ResultadoAdapter(getApplicationContext(), resultados) {
                    @Override
                    public void detalhes(Resultado resultado) {
                        Intent intent = new Intent(PesquisarActivity.this, DetalheActivity.class);

                        Bundle params = new Bundle();
                        params.putSerializable("resultado", resultado);
                        intent.putExtras(params);
                        startActivity(intent);

                    }
                };
                finalFragment.setAdapter(adapter);

            }
        });

    }

    @Override
    public void onBackPressed() {
        if(result.isDrawerOpen()){
            result.closeDrawer();
        }
        else{
            voltarMenuPrincipal();
        }
    }

    private void voltarMenuPrincipal(){
        Intent menuPrincipalView =
                new Intent(PesquisarActivity.this, MainActivity.class);
        menuPrincipalView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(menuPrincipalView);
    }

}
