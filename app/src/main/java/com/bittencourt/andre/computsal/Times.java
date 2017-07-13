package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
import java.util.List;

import Model.Time;

public class Times extends AppCompatActivity {

    private TimesAdapter adapter;
    private ListView time_listview;
    private List<Time> times = new ArrayList<Time>();
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_times);

        time_listview = (ListView) findViewById(R.id.lista_times);

        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        layout.setVisibility(View.VISIBLE);
        time_listview.setVisibility(View.GONE);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Times");

        adapter = new TimesAdapter(this, times);
        time_listview.setAdapter(adapter);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);
                times.add(novo);
                adapter.notifyDataSetChanged();
                layout.setVisibility(View.GONE);
                time_listview.setVisibility(View.VISIBLE);
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user.getEmail().equals("bittencourtandre@hotmail.com") || user.getEmail().equals("pedrocastro.coutinho@gmail.com") || user.getEmail().equals("igorbonomo@hotmail.com") || user.getEmail().equals("brenoriosfe@hotmail.com")) {
                startActivity(new Intent(Times.this, Criar_time.class));
            }
            else{
                Toast.makeText(Times.this, "Você não possui permissão para criar time.", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Times.this,Principal.class));
        super.onBackPressed();
    }

}
