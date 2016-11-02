package com.loterias.caixa.megasena.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loterias.caixa.megasena.Acitivity.DetalheActivity;
import com.loterias.caixa.megasena.Adapter.ResultadoAdapter;
import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.R;

import java.util.ArrayList;
import java.util.List;

public class ResultadoFragment extends Fragment {

    public RecyclerView mRecyclerView;
    private List<Resultado> mList;
    private BancoDeDados bd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//                ResultadoAdapter adapter = (ResultadoAdapter) recyclerView.getAdapter();
//                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
//                    bd = new BancoDeDados(getActivity());
//                    bd.abrir();
//                    Integer ultimo = mList.size();
//                    List<Resultado> listAux = bd.getResultados100(ultimo.toString());
//                    bd.fechar();
//                    for (int i = 0; i < listAux.size(); i++) {
//                        adapter.addListItem(listAux.get(i), mList.size());
//                    }
//                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        //Recupera informações do banco de dados
        bd = new BancoDeDados(getActivity());
        bd.abrir();
        mList = bd.getResultados();
        bd.fechar();

        ResultadoAdapter adapter = new ResultadoAdapter(getActivity(), mList) {
            @Override
            public void detalhes(Resultado resultado) {
                Intent intent = new Intent(getActivity(), DetalheActivity.class);

                Bundle params = new Bundle();
                params.putSerializable("resultado", resultado);
                intent.putExtras(params);
                startActivity(intent);

            }
        };
        mRecyclerView.setAdapter(adapter);


        return view;
    }

    public RecyclerView.Adapter getAdapter() {
        return mRecyclerView.getAdapter();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

}
