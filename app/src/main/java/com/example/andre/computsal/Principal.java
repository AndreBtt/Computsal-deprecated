package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {

    private ImageView mTimes;
    private ImageView mSair;
    private ImageView mArtilheiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        mTimes = (ImageView) findViewById(R.id.Times_jogadores);

        mSair = (ImageView) findViewById(R.id.sair);

        mArtilheiro = (ImageView) findViewById(R.id.Artilheiro);

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

    }

//    @Override
//    public void onBackPressed() {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(Principal.this,Criar_logar.class));
//        super.onBackPressed();
//    }
}
