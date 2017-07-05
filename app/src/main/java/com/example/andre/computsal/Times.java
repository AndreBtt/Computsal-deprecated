package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import DAO.Ler_times;
import Model.Time;

public class Times extends AppCompatActivity {


    private ArrayList<Time> Times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_times);

        Times = Ler_times.extrair_time();

        ListView time_listview = (ListView) findViewById(R.id.lista);

        final TimesAdapter adapter = new TimesAdapter(this, Times);

        time_listview.setAdapter(adapter);

        time_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Time time_atual = adapter.getItem(position);

                String nome_time = time_atual.getNome_time();

                Intent proxima_pagina = new Intent(Times.this,Time_jogador.class);

                proxima_pagina.putExtra("nome",nome_time);

                startActivity(proxima_pagina);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.butao_add){
            startActivity(new Intent(Times.this, Criar_time.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
