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

import Model.Grupo;

public class ListaGrupoAdapter extends ArrayAdapter<Grupo>{

    Context context;

    public ListaGrupoAdapter(Context context, List<Grupo> grupos){
        super(context,0,grupos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (null == listItemView) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.tela_lista_grupo, parent, false);
        }

        Grupo atual = getItem(position);

        TextView grupo = (TextView) listItemView.findViewById(R.id.grupo);

        grupo.setText("Grupo " + atual.getLetra_grupo());

        return listItemView;
    }
}
