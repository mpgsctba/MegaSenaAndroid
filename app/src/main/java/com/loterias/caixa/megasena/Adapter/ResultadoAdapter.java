package com.loterias.caixa.megasena.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.R;
import com.loterias.caixa.megasena.Utils.Utils;

import java.util.List;

/**
 * Created by Marcos on 19/10/2016.
 */
public abstract class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.MyViewHolder> {

    private Context mContext;
    private List<Resultado> mList;
    private LayoutInflater mLayoutInflater;


    public ResultadoAdapter (Context c, List<Resultado> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addListItem(Resultado c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_resultado, parent, false);
        MyViewHolder mvh =  new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.dezena1.setImageResource(Utils.findImagemIdByNumero(mList.get(position).getDezena1()));
        holder.dezena2.setImageResource(Utils.findImagemIdByNumero(mList.get(position).getDezena2()));
        holder.dezena3.setImageResource(Utils.findImagemIdByNumero(mList.get(position).getDezena3()));
        holder.dezena4.setImageResource(Utils.findImagemIdByNumero(mList.get(position).getDezena4()));
        holder.dezena5.setImageResource(Utils.findImagemIdByNumero(mList.get(position).getDezena5()));
        holder.dezena6.setImageResource(Utils.findImagemIdByNumero(mList.get(position).getDezena6()));
        holder.concursoIdItem.setText(mList.get(position).getConcursoId().toString());
        holder.btnDetalhes.setImageResource(R.drawable.ic_seta);
        holder.btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detalhes(mList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract void detalhes(Resultado resultado);

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView dezena1;
        public ImageView dezena2;
        public ImageView dezena3;
        public ImageView dezena4;
        public ImageView dezena5;
        public ImageView dezena6;
        public TextView concursoIdItem;
        public ImageButton btnDetalhes;

        public MyViewHolder(final View itemView) {
            super(itemView);

            dezena1 = (ImageView) itemView.findViewById(R.id.dezena01);
            dezena2 = (ImageView) itemView.findViewById(R.id.dezena02);
            dezena3 = (ImageView) itemView.findViewById(R.id.dezena03);
            dezena4 = (ImageView) itemView.findViewById(R.id.dezena04);
            dezena5 = (ImageView) itemView.findViewById(R.id.dezena05);
            dezena6 = (ImageView) itemView.findViewById(R.id.dezena06);
            concursoIdItem = (TextView) itemView.findViewById(R.id.concursoIdItem);
            btnDetalhes = (ImageButton) itemView.findViewById(R.id.btnDetalhes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BancoDeDados bd = new BancoDeDados(v.getContext());
                    bd.abrir();
                    Resultado resultado = bd.getResultado(Integer.valueOf(concursoIdItem.getText().toString()));
                    bd.fechar();
                    detalhes(resultado);
                }
            });
        }
    }
}
