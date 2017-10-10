package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private LinearLayout mTimes;
    private LinearLayout mSair;
    private LinearLayout mArtilheiro;
    private LinearLayout mGerenciar;
    private LinearLayout mGrupo;
    private LinearLayout mJogos_passados;
    private LinearLayout mSobre_autor;
    private LinearLayout mTabelas;
    private LinearLayout mAgendar;

    private ArrayList<String> mAdm = new ArrayList<>();
    DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Adm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String novo = dataSnapshot.getValue(String.class);
                mAdm.add(novo);
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

        mTabelas = (LinearLayout) findViewById(R.id.tabelas);

        mSobre_autor = (LinearLayout) findViewById(R.id.sobre);

        mTimes = (LinearLayout) findViewById(R.id.Times_jogadores);

        mSair = (LinearLayout) findViewById(R.id.sair);

        mArtilheiro = (LinearLayout) findViewById(R.id.Artilheiro);

        mGrupo = (LinearLayout) findViewById(R.id.grupos);

        mGerenciar = (LinearLayout) findViewById(R.id.gerenciar);

        mJogos_passados = (LinearLayout) findViewById(R.id.jogos_passados);

        mAgendar = (LinearLayout) findViewById(R.id.horario);

        mAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PASSAR SE É CAPITAO
                startActivity(new Intent(Principal.this, AgendarHorario.class));
            }
        });

        mTabelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Grupos_tabela.class));
            }
        });

        mJogos_passados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Jogos_anteriores.class));
            }
        });

        mTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Times.class));
            }
        });

        mSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Principal.this,Criar_logar.class));
            }
        });

        mArtilheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Artilharia.class));
            }
        });

        mGerenciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user == null)
                    Toast.makeText(Principal.this, "Você não possui permissão para acessar essa área.", Toast.LENGTH_SHORT).show();
                else if(mAdm.contains(user.getEmail()))
                        startActivity(new Intent(Principal.this, Gerenciar.class));
                else
                    Toast.makeText(Principal.this, "Você não possui permissão para acessar essa área.", Toast.LENGTH_SHORT).show();
            }
        });

        mGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Grupos.class));
            }
        });

        mSobre_autor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Sobre_autor.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(Principal.this,Criar_logar.class));
        }
        else startActivity(new Intent(Principal.this,Principal.class));
        super.onBackPressed();
    }
}