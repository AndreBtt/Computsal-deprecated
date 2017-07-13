package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Jogo;

public class Todos_jogos extends AppCompatActivity {

    private JogosAdapter adapter;
    private ListView listview;
    private List<Jogo> jogos = new ArrayList<Jogo>();
    private LinearLayout layout;

    private DatabaseReference mBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_times);

        mBanco = FirebaseDatabase.getInstance().getReference("Jogos");

        TextView textinho = (TextView) findViewById(R.id.textinho);

        textinho.setText("Carregando Jogos");

        listview = (ListView) findViewById(R.id.lista_times);

        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        layout.setVisibility(View.VISIBLE);
        listview.setVisibility(View.GONE);

        adapter = new JogosAdapter(this,jogos);
        listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogo novo = dataSnapshot.getValue(Jogo.class);
                jogos.add(novo);
                adapter.notifyDataSetChanged();
                layout.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Jogo atual = adapter.getItem(i);

                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(atual.getG1());
                String g1 = sb.toString();

                sb = new StringBuilder();
                sb.append("");
                sb.append(atual.getG2());
                String g2 = sb.toString();

                Intent proxima_pagina = new Intent(Todos_jogos.this,Jogo_config.class);

                proxima_pagina.putExtra("t1",atual.getT1());
                proxima_pagina.putExtra("t2",atual.getT2());
                proxima_pagina.putExtra("g1",g1);
                proxima_pagina.putExtra("g2",g2);

                startActivity(proxima_pagina);

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Todos_jogos.this,Gerenciar.class));
        super.onBackPressed();
    }
}
