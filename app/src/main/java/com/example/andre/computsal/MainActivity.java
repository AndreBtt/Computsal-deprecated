package com.example.andre.computsal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button mLogin,mCreate;

    private EditText mEmail,mSenha;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mLogin = (Button) findViewById(R.id.login);
        mCreate = (Button) findViewById(R.id.create_acount);

        mEmail = (EditText) findViewById(R.id.user_email);
        mSenha = (EditText) findViewById(R.id.user_senha);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LOGIN", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LOGIN", "onAuthStateChanged:signed_out");
                }
            }
        };

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("clicou", "login");
            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("clicou", "criar");

                String email = mEmail.getText().toString().trim();
                String senha = mSenha.getText().toString().trim();

                if(valido()){
                    Criar_usuario(email,senha);
                }

            }
        });
    }

    private boolean valido() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Obrigatório.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        String password = mSenha.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mSenha.setError("Obrigatório.");
            valid = false;
        } else {
            mSenha.setError(null);
        }

        return valid;
    }

    public void Criar_usuario(String email, String senha){
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if(task.isSuccessful()){
                            Log.d("Criando novo Usuario", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        }

                        // If sign in fails, display a message to the user.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Email inválido ou já cadastrado.",
                                    Toast.LENGTH_SHORT).show();
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

}
