package com.loterias.caixa.megasena.Acitivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Constante;
import com.loterias.caixa.megasena.Utils.Utils;
import com.loterias.caixa.megasena.WebService.Sincronismo;
import com.loterias.caixa.megasena.WebService.WebService;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Drawer result;
    private MainActivity mainActivity;
    private ProgressBar progressBar;
    private Boolean ultimoQuebrado = false;
    private Button atualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mainActivity = this;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.teste_logo)
                .withTranslucentStatusBar(false)
                .build();

        //ULTIMO CONCURSO
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().
                withSelectable(true).
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
                            Log.d("LOG","Nothing still");
                        } else if (drawerItem.getIdentifier() == Constante.MENU_PESQUISAR) {
                            Intent intent = new Intent(MainActivity.this, PesquisarActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_SURPRESINHA) {
                            Intent intent = new Intent(MainActivity.this, SurpresinhaActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_RANKING) {
                            Intent intent = new Intent(MainActivity.this, RankingActivity.class);
                            startActivity(intent);
                        } else if (drawerItem.getIdentifier() == Constante.MENU_ESTATISTICA) {
                            Intent intent = new Intent(MainActivity.this, EscolherNumerosActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();

        //RECUPERA ULTIMO CONCURSO
        BancoDeDados bd = new BancoDeDados(this);
        bd.abrir();
        Resultado resultado = bd.getUltimoConsurso();
        bd.fechar();

        //ATUALIZA TELA
        atualizarTela(resultado);
        atualizar = (Button) findViewById(R.id.atualizar);
        atualizar.setVisibility(View.GONE);
        final Integer concursoId = resultado.getConcursoId();

        if (resultado.getProximoSorteio() == null) {
            ultimoQuebrado = true;
            atualizar.setVisibility(View.VISIBLE);

        } else {
            ultimoQuebrado = false;
            if (necessitaAtualizar(resultado)) {
                atualizar.setVisibility(View.VISIBLE);
            }
        }

        atualizar = (Button) findViewById(R.id.atualizar);
        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (!Utils.verificaConexao(view.getContext())) {
                    Snackbar.make(view, "Sem conexão com internet!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                view.setVisibility(View.GONE);

                //RECUPERA ULTIMO CONCURSO NOVAMENTE
                BancoDeDados bd = new BancoDeDados(view.getContext());
                bd.abrir();
                Resultado resultado = bd.getUltimoConsurso();
                bd.fechar();

                if (resultado.getConcursoId() > concursoId) {
                    //ATUALIZAR DADOS TELA
                    atualizarTela(resultado);
                    if (resultado.getProximoSorteio() == null) {
                        view.setVisibility(View.VISIBLE);
                        Snackbar.make(view, "Último concurso em processamento, tente novamente!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                } else {
                    //BUSCA NOVO RESULTADO
                    baixarResultados();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(result.isDrawerOpen()){
            result.closeDrawer();
        }
        else{
            super.onBackPressed();
        }
    }

    private Boolean necessitaAtualizar(Resultado resultado) {
        final Integer concursoId = resultado.getConcursoId();
        Calendar cal = Calendar.getInstance();
        Calendar dataProximo = Calendar.getInstance();
        dataProximo.setTime(resultado.getProximoSorteio());
        Date proximo = resultado.getProximoSorteio();
        Date hoje = Utils.extrairData(new Date());
        //data atual maior que proximo concurso
        Boolean condicao1 = (cal.compareTo(dataProximo) > 0);
        Calendar dataHoje = Calendar.getInstance();
        dataHoje.setTime(hoje);
        //se proximo concurso for hoje, verifica se ja passou das 21h.
        Boolean condicao2 = true;
        if (dataProximo.compareTo(dataHoje) == 0) {
            if (cal.get(Calendar.HOUR_OF_DAY) < 21) {
                condicao2 = false;
            }
        }
        return condicao1 && condicao2;
    }

    private void baixarResultados() {
        DownloadUltimo thread = new DownloadUltimo();
        thread.execute();
    }

    private class DownloadUltimo extends AsyncTask<Void, Void, Void> {
        private Sincronismo sincronismo = null;

        @Override
        protected void onPreExecute() {
            mainActivity.progressBar.setVisibility(View.VISIBLE);
            sincronismo = new Sincronismo(mainActivity, ultimoQuebrado);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mainActivity.progressBar.setVisibility(View.GONE);

            //RECUPERA ULTIMO CONCURSO NOVAMENTE
            BancoDeDados bd = new BancoDeDados(mainActivity);
            bd.abrir();
            Resultado resultado = bd.getUltimoConsurso();
            bd.fechar();

            if (resultado.getProximoSorteio() == null) {
                mainActivity.atualizar.setVisibility(View.VISIBLE);
                Snackbar.make(mainActivity.getCurrentFocus(), "Último concurso em processamento, tente novamente!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            } else {
                mainActivity.atualizarTela(resultado);
                //mainActivity.recreate();
                Snackbar.make(mainActivity.getCurrentFocus(), "Resultados Atualizados!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            sincronismo.baixarJogos();
            return null;
        }
    }

    private void atualizarTela(Resultado resultado) {
        //SET ATRIBUTOS DE TELA
        TextView tvConcursoId = (TextView) findViewById(R.id.concurso);
        tvConcursoId.setText(resultado.getConcursoId().toString());

        TextView data = (TextView) findViewById(R.id.data);
        data.setText(Utils.formateDate(resultado.getDataSorteio()));

        TextView status = (TextView) findViewById(R.id.status);
        status.setText((resultado.isAcumulado()) ? "Acumulou!" : "Saiu!");

        ImageView dezena01 = (ImageView) findViewById(R.id.dezena01);
        dezena01.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena1()));

        ImageView dezena02 = (ImageView) findViewById(R.id.dezena02);
        dezena02.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena2()));

        ImageView dezena03 = (ImageView) findViewById(R.id.dezena03);
        dezena03.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena3()));

        ImageView dezena04 = (ImageView) findViewById(R.id.dezena04);
        dezena04.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena4()));

        ImageView dezena05 = (ImageView) findViewById(R.id.dezena05);
        dezena05.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena5()));

        ImageView dezena06 = (ImageView) findViewById(R.id.dezena06);
        dezena06.setImageResource(Utils.findImagemIdByNumero(resultado.getDezena6()));

        TextView vlrPremio = (TextView) findViewById(R.id.vlrProxSorteio);
        vlrPremio.setText(Utils.formateValor(resultado.getEstimativaPremio()));

        TextView dataProximo = (TextView) findViewById(R.id.dataProximo);
        dataProximo.setText(Utils.formateDate(resultado.getProximoSorteio()));

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



//ANIMATIONS
/*        dezena01 = (ImageView) findViewById(R.id.dezena01);
        dezena01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RotateIn)
                        .duration(700)
                        .playOn(dezena01);
            }
        });*/
