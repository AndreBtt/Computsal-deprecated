package com.bittencourt.andre.computsal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Criar_logar extends AppCompatActivity {

    private Button mLogin,mCreate;

    private EditText mEmail,mSenha;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressDialog mProgressDialog;

    private Button mEsqueci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_logar);

        mAuth = FirebaseAuth.getInstance();

        mLogin = (Button) findViewById(R.id.login);
        mCreate = (Button) findViewById(R.id.create_acount);

        mEmail = (EditText) findViewById(R.id.user_email);
        mSenha = (EditText) findViewById(R.id.user_senha);

        mEsqueci = (Button) findViewById(R.id.esqueci_senha);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(Criar_logar.this, Principal.class));
                    }
                    else {
                        Toast.makeText(Criar_logar.this,"Confime o email...",Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String senha = mSenha.getText().toString().trim();

                if(valido()){
                    SignIn(email,senha);
                }

            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String senha = mSenha.getText().toString().trim();

                if(valido()){
                    Criar_usuario(email,senha);
                }

            }
        });

        mEsqueci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Criar_logar.this, "Insira seu email", Toast.LENGTH_SHORT).show();
                }
                else Esqueci_senha(email);
            }
        });
    }

    private void Esqueci_senha(String email){

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Criar_logar.this, "Email enviado", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Criar_logar.this, "Esse email não é cadastrado", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Criar_logar.this,"Senha deve possuir no mínimo 6 caracteres",Toast.LENGTH_LONG).show();
                valid = false;
            }
            mSenha.setError(null);
        }

        return valid;
    }

    private void Criar_usuario(String email, String senha){

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if(task.isSuccessful()){
                            sendEmailVerification();
                        }
                        // If sign in fails, display a message to the user.
                        else {
                            Toast.makeText(Criar_logar.this, "Email inválido ou já cadastrado.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SignIn(String email, String password) {

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("Logando...");

        mProgressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Criar_logar.this, "Email ou Senha incorretos.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        mProgressDialog.dismiss();
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
                            Toast.makeText(Criar_logar.this, "Falha ao enviar email de confirmação.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Criar_logar.this,Criar_logar.class));
        super.onBackPressed();
    }

}
