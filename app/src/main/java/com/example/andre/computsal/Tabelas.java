package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Tabelas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_tabelas);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Tabelas.this,Grupos_tabela.class));
        super.onBackPressed();
    }

}
