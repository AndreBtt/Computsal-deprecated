package com.bit.andre.computsal;

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

import Model.Time;

public class Time_grupo extends AppCompatActivity {

    private TimesAdapter adapter;
    private ListView time_listview;
    private List<Time> times = new ArrayList<Time>();
    String t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_times);

        time_listview = (ListView) findViewById(R.id.lista_times);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Times");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            t1 = extras.getString("time1");
            t2 = extras.getString("time2");
            t3 = extras.getString("time3");
            t4 = extras.getString("time4");
        }

        adapter = new TimesAdapter(this, times);
        time_listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);
                if(novo.getNome_time().equals(t1) || novo.getNome_time().equals(t2) ||novo.getNome_time().equals(t3) ||novo.getNome_time().equals(t4)){
                    times.add(novo);
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

        time_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Time time_atual = adapter.getItem(position);

                Bundle b=new Bundle();
                b.putString("time", time_atual.getNome_time());
                Intent proxima_pagina = new Intent(Time_grupo.this,Time_jogador.class);

                proxima_pagina.putExtras(b);

                startActivity(proxima_pagina);
            }
        });
    }

}
