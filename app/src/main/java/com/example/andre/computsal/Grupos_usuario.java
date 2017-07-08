package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Grupo;

public class Grupos_usuario extends AppCompatActivity {

    private ListaGrupoAdapter adapter;
    private ListView time_listview;
    private List<Grupo> grupos = new ArrayList<Grupo>();
    private DatabaseReference mBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_times);

        time_listview = (ListView) findViewById(R.id.lista_times);

        mBanco = FirebaseDatabase.getInstance().getReference("Grupos");

        adapter = new ListaGrupoAdapter(this,grupos);
        time_listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grupo novo = dataSnapshot.getValue(Grupo.class);
                grupos.add(novo);
                adapter.notifyDataSetChanged();
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

        time_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Grupo atual = adapter.getItem(position);

                Bundle b = new Bundle();
                b.putString("time1", atual.getT1());
                b.putString("time2", atual.getT2());
                b.putString("time3", atual.getT3());
                b.putString("time4", atual.getT4());

                Intent proxima_pagina = new Intent(Grupos_usuario.this,Time_grupo.class);

                proxima_pagina.putExtras(b);

                startActivity(proxima_pagina);
            }
        });

    }
}
