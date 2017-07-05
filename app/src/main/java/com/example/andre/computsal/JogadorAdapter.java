package com.example.andre.computsal;

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

public class JogadorAdapter extends ArrayAdapter<Jogador> {

    private Context context;

    public JogadorAdapter(Context context, List<Jogador> jogadores){
        super(context,0,jogadores);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (null == listItemView) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.lista_jogadores, parent, false);
        }

        String nome_atual = getItem(position).getNome();
        int gol_atual = getItem(position).getGol();

        TextView nome = (TextView) listItemView.findViewById(R.id.jogador);
        TextView gols = (TextView) listItemView.findViewById(R.id.gols);

        gols.setText(0);
        nome.setText("andre");

        return listItemView;
    }
}
