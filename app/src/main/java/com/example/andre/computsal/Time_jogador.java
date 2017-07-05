package com.example.andre.computsal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Jogador;

public class Time_jogador extends AppCompatActivity {

    private ListView tela_lista;
    private JogadorAdapter adapter;
    private List<Jogador> jogadores = new ArrayList<Jogador>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_time_jogador);

        Bundle b = this.getIntent().getExtras();
        final String time = b.getString("time");

        tela_lista = (ListView) findViewById(R.id.lista);

        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("Jogadores");

        adapter = new JogadorAdapter(this,jogadores);
        tela_lista.setAdapter(adapter);

        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogador novo = dataSnapshot.getValue(Jogador.class);
                if(novo.getTime().equals(time))jogadores.add(novo);
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

    }
}
