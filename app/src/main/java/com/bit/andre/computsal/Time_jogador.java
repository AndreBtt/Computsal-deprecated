package com.bit.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Jogador;

public class Time_jogador extends AppCompatActivity {

    private Time_jogadorAdapter adapter;
    private ListView listview;
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private String email;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_time_jogador);

        Bundle b = this.getIntent().getExtras();
        time = b.getString("time");
        email = b.getString("email");

        listview = (ListView) findViewById(R.id.lista_time_jogador);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Jogadores");

        adapter = new Time_jogadorAdapter(this,jogadores);
        listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogador novo = dataSnapshot.getValue(Jogador.class);
                if(novo.getTime().equals(time)) {
                    jogadores.add(novo);
                    Collections.sort(jogadores, new Comparator<Jogador>() {
                        public int compare(Jogador c1, Jogador c2) {
                            if (c1.getGol() > c2.getGol()) return -1;
                            if (c1.getGol() < c2.getGol()) return 1;
                            return 0;
                        }});
                    adapter.notifyDataSetChanged();
                }
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.atualizar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.butao_atualizar){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user == null){
                Toast.makeText(this, "Você não possui permissão", Toast.LENGTH_SHORT).show();
            }
            else if(email.equals(user.getEmail())){
                Bundle b = new Bundle();
                b.putString("time", time);
                Intent proxima_pagina = new Intent(Time_jogador.this,Atualizar_time.class);
                proxima_pagina.putExtras(b);
                startActivity(proxima_pagina);
            }
            else Toast.makeText(this, "Você não possui permissão", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
