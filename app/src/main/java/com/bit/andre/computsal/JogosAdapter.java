package com.bit.andre.computsal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Jogo;

public class JogosAdapter extends ArrayAdapter<Jogo> {

    Context context;

    public JogosAdapter(Context context, List<Jogo> jogos){
        super(context,0,jogos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.tela_todos_jogos, parent, false);
        }

        Jogo atual = getItem(position);

        TextView t1 = (TextView) listItemView.findViewById(R.id.t1);
        TextView t2 = (TextView) listItemView.findViewById(R.id.t2);
        TextView placar = (TextView) listItemView.findViewById(R.id.placar);

        t1.setText(atual.getT1());
        t2.setText(atual.getT2());

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(atual.getG1());
        String strI = sb.toString();

        strI += " x ";

        sb = new StringBuilder();
        sb.append("");
        sb.append(atual.getG2());
        strI += sb;

        placar.setText(strI);

        return listItemView;
    }
}
