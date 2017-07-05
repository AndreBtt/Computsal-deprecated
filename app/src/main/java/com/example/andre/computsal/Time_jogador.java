package com.example.andre.computsal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Time_jogador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_time_jogador);

        String time_nome = getIntent().getExtras().getString("nome");

    }
}
