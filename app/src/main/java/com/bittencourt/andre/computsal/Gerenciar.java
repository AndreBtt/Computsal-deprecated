package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Gerenciar extends AppCompatActivity {

    private LinearLayout mTodos_jogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_gerenciar);

        mTodos_jogos = (LinearLayout) findViewById(R.id.todos_jogos);

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
