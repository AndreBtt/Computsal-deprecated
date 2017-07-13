package com.bittencourt.andre.computsal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Jogador;

public class ArtilhariaAdapter extends ArrayAdapter<Jogador>{

    Context context;

    public ArtilhariaAdapter(Context context, List<Jogador> jogadores){
        super(context,0,jogadores);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (null == listItemView) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.tela_artilharia, parent, false);
        }

        Jogador atual = getItem(position);

        TextView nome = (TextView) listItemView.findViewById(R.id.nome_jogador);
        TextView time = (TextView) listItemView.findViewById(R.id.time_jogador);
        TextView gols = (TextView) listItemView.findViewById(R.id.gols_jogador);

        int gol = atual.getGol();

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(gol);
        String strI = sb.toString();

        gols.setText(sb);
        nome.setText(atual.getNome());
        time.setText(atual.getTime());

        return listItemView;
    }
}
