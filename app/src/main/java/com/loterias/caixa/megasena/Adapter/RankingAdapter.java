package com.loterias.caixa.megasena.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loterias.caixa.megasena.Model.Ranking;
import com.loterias.caixa.megasena.R;

import java.util.List;

/**
 * Created by Marcos on 13/10/2015.
 */
public class RankingAdapter extends BaseAdapter {
    private List<Ranking> rankings;
    private LayoutInflater inflater;

    public RankingAdapter(Context context, List<Ranking> rankings){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rankings = rankings;
    }

    @Override
    public int getCount() {
        return rankings.size();
    }

    @Override
    public Object getItem(int position) {
        return rankings.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        View v = inflater.inflate(R.layout.item_ranking, null);
        Integer numero = rankings.get(i).getNumero();
        Integer quantidade = rankings.get(i).getQuantidade();

        ((TextView)(v.findViewById(R.id.numeroList))).setText((numero.toString().length() == 1) ? "0"+numero : numero.toString());
        ((TextView)(v.findViewById(R.id.vezesList))).setText(quantidade.toString() + " vezes");

        return v;
    }

}
