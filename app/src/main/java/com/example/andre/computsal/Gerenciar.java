package com.example.andre.computsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Gerenciar extends AppCompatActivity {

    private ImageView mGrupo;
    private ImageView mTodos_jogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_gerenciar);

        mGrupo = (ImageView) findViewById(R.id.Add_grupo);

        mTodos_jogos = (ImageView) findViewById(R.id.todos_jogos);

        mGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gerenciar.this,Grupos.class));
            }
        });

        mTodos_jogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gerenciar.this,Todos_jogos.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Gerenciar.this,Principal.class));
        super.onBackPressed();
    }
}
