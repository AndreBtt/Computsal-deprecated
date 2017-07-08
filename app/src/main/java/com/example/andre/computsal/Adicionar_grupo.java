package com.example.andre.computsal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Time;

public class Adicionar_grupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_adicionar_grupo);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Times");

        final List<String> areas = new ArrayList<String>();

        Spinner primeiro = (Spinner) findViewById(R.id.primeiro);
        Spinner segundo = (Spinner) findViewById(R.id.segundo);
        Spinner terceiro = (Spinner) findViewById(R.id.terceiro);
        Spinner quarto = (Spinner) findViewById(R.id.quarto);

        final ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(Adicionar_grupo.this, R.layout.spinner_item, areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primeiro.setAdapter(areasAdapter);
        segundo.setAdapter(areasAdapter);
        terceiro.setAdapter(areasAdapter);
        quarto.setAdapter(areasAdapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);
                areas.add(novo.getNome_time());
                areasAdapter.notifyDataSetChanged();

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
