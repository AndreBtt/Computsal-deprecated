package com.example.andre.computsal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

public class Times extends AppCompatActivity {

    private TimesAdapter adapter;
    private ListView time_listview;
    private List<Time> times = new ArrayList<Time>();
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_times);

        mDialog = new ProgressDialog(this);

        mDialog.setMessage("Gerando lista de times...");

        mDialog.show();

        time_listview = (ListView) findViewById(R.id.lista);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Times");

        adapter = new TimesAdapter(this, times);
        time_listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);
                times.add(novo);
                adapter.notifyDataSetChanged();
                mDialog.dismiss();
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
                Intent proxima_pagina = new Intent(Times.this,Time_jogador.class);

                proxima_pagina.putExtras(b);

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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Times.this,Principal.class));
        super.onBackPressed();
    }

}
