package com.example.andre.computsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Gerenciar extends AppCompatActivity {

    private ImageView grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_gerenciar);

        grupo = (ImageView) findViewById(R.id.Add_grupo);

        grupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Gerenciar.this,Grupos_usuario.class));
            }
        });


    }
}
