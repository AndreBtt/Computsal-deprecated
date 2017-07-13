package com.bittencourt.andre.computsal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Criar_conta extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mEmail,mSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_conta);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById(R.id.user_email);
        mSenha = (EditText) findViewById(R.id.user_senha);

        Button criar = (Button) findViewById(R.id.criar_conta);

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valido()){
                    Criar_usuario(mEmail.getText().toString().trim(),mSenha.getText().toString().trim());
                }

            }
        });
    }

    private void Criar_usuario(String email, String senha){

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            sendEmailVerification();
                            TextView txt = (TextView) findViewById(R.id.textinho);
                            txt.setVisibility(View.VISIBLE);
                            Toast.makeText(Criar_conta.this, "Email enviado.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Criar_conta.this, "Email inválido ou já cadastrado.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendEmailVerification() {

        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(Criar_conta.this, "Falha ao enviar email de confirmação.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean valido() {
        boolean valid = true;

        String email = mEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Obrigatório.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mSenha.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            mSenha.setError("Obrigatório.");
            valid = false;
        } else {
            if(password.length() < 6){
                Toast.makeText(Criar_conta.this,"Senha deve possuir no mínimo 6 caracteres",Toast.LENGTH_LONG).show();
                valid = false;
            }
            mSenha.setError(null);
        }

        return valid;
    }
}
