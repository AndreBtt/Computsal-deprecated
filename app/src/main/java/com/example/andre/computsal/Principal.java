package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Principal extends AppCompatActivity {

    private ImageView mTimes;
    private ImageView mSair;
    private ImageView mArtilheiro;
    private ImageView mGerenciar;
    private ImageView mGrupo;
    private ImageView mJogos_passados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        mTimes = (ImageView) findViewById(R.id.Times_jogadores);

        mSair = (ImageView) findViewById(R.id.sair);

        mArtilheiro = (ImageView) findViewById(R.id.Artilheiro);

        mGrupo = (ImageView) findViewById(R.id.grupos);

        mGerenciar = (ImageView) findViewById(R.id.gerenciar);

        mJogos_passados = (ImageView) findViewById(R.id.jogos_passados);

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
