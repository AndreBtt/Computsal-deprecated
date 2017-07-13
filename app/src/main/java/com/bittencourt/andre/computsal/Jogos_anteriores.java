package com.bittencourt.andre.computsal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class Jogos_anteriores extends AppCompatActivity {

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
                if(novo.getFim()) {
                    jogos.add(novo);
                    adapter.notifyDataSetChanged();
                    layout.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
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
}
