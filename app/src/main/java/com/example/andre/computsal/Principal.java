package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Principal extends AppCompatActivity {

    private LinearLayout mTimes;
    private LinearLayout mSair;
    private LinearLayout mArtilheiro;
    private LinearLayout mGerenciar;
    private LinearLayout mGrupo;
    private LinearLayout mJogos_passados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        mTimes = (LinearLayout) findViewById(R.id.Times_jogadores);

        mSair = (LinearLayout) findViewById(R.id.sair);

        mArtilheiro = (LinearLayout) findViewById(R.id.Artilheiro);

        mGrupo = (LinearLayout) findViewById(R.id.grupos);

        mGerenciar = (LinearLayout) findViewById(R.id.gerenciar);

        mJogos_passados = (LinearLayout) findViewById(R.id.jogos_passados);

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
                if(user.getEmail().equals("bittencourtandre@hotmail.com")) {
                    startActivity(new Intent(Principal.this, Gerenciar.class));
                }
                else{
                    Toast.makeText(Principal.this, "Você não possui permissão para acessar essa área.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,Grupos_usuario.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Principal.this,Principal.class));
        super.onBackPressed();
    }
}
