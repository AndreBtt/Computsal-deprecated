package com.bittencourt.andre.computsal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import Model.Time;

public class TimesAdapter extends ArrayAdapter<Time> {

    private Context context;

    public TimesAdapter(Context context, List<Time> times){
        super(context,0,times);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;

        if (null == listItemView) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.tela_times, parent, false);
        }

        Time atual = getItem(position);

        ImageView logo = (ImageView) listItemView.findViewById(R.id.logo);
        TextView nome = (TextView) listItemView.findViewById(R.id.nome_time);

        Glide.with(context).load(atual.getLogo()).into(logo);

        nome.setText(atual.getNome_time());

        return listItemView;
    }

}

