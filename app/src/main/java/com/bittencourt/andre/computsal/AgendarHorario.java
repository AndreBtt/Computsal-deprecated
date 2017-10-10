package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

public class AgendarHorario extends AppCompatActivity {

    private DatabaseReference mBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_agendar_horario);



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AgendarHorario.this,Principal.class));
        super.onBackPressed();
    }

}
