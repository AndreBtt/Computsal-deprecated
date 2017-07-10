package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import Model.Grupo;

public class Grupos extends AppCompatActivity {

    private ListaGrupoAdapter adapter;
    private ListView time_listview;
    private List<Grupo> grupos = new ArrayList<Grupo>();
    private DatabaseReference mBanco;
    private char mLetra;

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

                Intent proxima_pagina = new Intent(Grupos.this,Time_grupo.class);

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
            if(user.getEmail().equals("bittencourtandre@hotmail.com")) {
                Intent intent = new Intent(Grupos.this, Criar_grupo.class);
                String s = "";
                int dif = grupos.size();
                mLetra = (char) ('A' + dif);
                s += mLetra;
                intent.putExtra("grupo",s);
                startActivity(intent);
            }
            else{
                Toast.makeText(Grupos.this, "Você não possui permissão para acessar essa área.", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Grupos.this,Principal.class));
        super.onBackPressed();
    }
}
