package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.Grupo;
import Model.Jogo;
import Model.Time;

public class Criar_grupo extends AppCompatActivity {

    DatabaseReference mBanco;

    String grupo;

    TextView texto_inicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_grupo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            grupo = extras.getString("grupo");
        }

        texto_inicial = (TextView) findViewById(R.id.texto_inicial);

        texto_inicial.setText("Criando grupo "  + grupo);

        DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Times");

        final List<String> areas = new ArrayList<String>();

        final Spinner primeiro = (Spinner) findViewById(R.id.primeiro);
        final Spinner segundo = (Spinner) findViewById(R.id.segundo);
        final Spinner terceiro = (Spinner) findViewById(R.id.terceiro);
        final Spinner quarto = (Spinner) findViewById(R.id.quarto);

        final ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(Criar_grupo.this, R.layout.spinner_item, areas);
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

        Button criar = (Button) findViewById(R.id.criar_grupo);

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference Banco = FirebaseDatabase.getInstance().getReference("Grupos");

                String t1,t2,t3,t4;

                t1 = primeiro.getSelectedItem().toString();
                t2 = segundo.getSelectedItem().toString();
                t3 = terceiro.getSelectedItem().toString();
                t4 = quarto.getSelectedItem().toString();

                Grupo g = new Grupo(t1,t2,t3,t4,grupo);

                DatabaseReference newPost = Banco.push();

                newPost.setValue(g);

                Banco = FirebaseDatabase.getInstance().getReference("Jogos");

                Jogo j1 = new Jogo(t1,t2,0,0,false);
                Jogo j2 = new Jogo(t1,t3,0,0,false);
                Jogo j3 = new Jogo(t1,t4,0,0,false);
                Jogo j4 = new Jogo(t2,t3,0,0,false);
                Jogo j5 = new Jogo(t2,t4,0,0,false);
                Jogo j6 = new Jogo(t3,t4,0,0,false);

                newPost = Banco.push();
                newPost.setValue(j1);

                newPost = Banco.push();
                newPost.setValue(j2);

                newPost = Banco.push();
                newPost.setValue(j3);

                newPost = Banco.push();
                newPost.setValue(j4);

                newPost = Banco.push();
                newPost.setValue(j5);

                newPost = Banco.push();
                newPost.setValue(j6);

                startActivity(new Intent(Criar_grupo.this,Grupos.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Criar_grupo.this,Grupos.class));
        super.onBackPressed();
    }

}
