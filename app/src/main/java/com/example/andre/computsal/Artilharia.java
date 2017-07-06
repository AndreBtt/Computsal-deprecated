package com.example.andre.computsal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

public class Artilharia extends AppCompatActivity{

    private ArtilhariaAdapter adapter;
    private ListView artilharia_listview;
    private List<Jogador> jogadores = new ArrayList<Jogador>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_artilharia);

        artilharia_listview = (ListView) findViewById(R.id.lista_artilharia);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Jogadores");

        adapter = new ArtilhariaAdapter(this,jogadores);
        artilharia_listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogador novo = dataSnapshot.getValue(Jogador.class);
                jogadores.add(novo);
                Collections.sort(jogadores, new Comparator<Jogador>() {
                    public int compare(Jogador c1, Jogador c2) {
                        if (c1.getGol() > c2.getGol()) return -1;
                        if (c1.getGol() < c2.getGol()) return 1;
                        return 0;
                    }});
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // MUDAR AQUI UPDATE (eu acho)
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
}
