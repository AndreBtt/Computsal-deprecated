package com.example.andre.computsal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Jogador;

public class Times extends AppCompatActivity {

    private DatabaseReference mDataBase;

    private Button b;

    private EditText nome,idade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_times);

        mDataBase = FirebaseDatabase.getInstance().getReference();

        b = (Button) findViewById(R.id.b);

        nome = (EditText) findViewById(R.id.nome);
        idade = (EditText) findViewById(R.id.idade);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewUser("boelo++",nome.getText().toString(),idade.getText().toString());
            }
        });
    }

    private void writeNewUser(String userId, String nome, String idade) {
        Jogador user = new Jogador(nome, idade);

        Toast.makeText(Times.this, "entrei",Toast.LENGTH_LONG).show();

        mDataBase.child("Times").child(userId).setValue(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
