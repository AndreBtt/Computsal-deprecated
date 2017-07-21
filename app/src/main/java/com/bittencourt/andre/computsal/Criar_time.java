package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import Model.Jogador;
import Model.Time;

import static com.bittencourt.andre.computsal.R.id.mostrar_imagem;

public class Criar_time extends AppCompatActivity {

    private Time user;

    private ArrayList<Jogador> jogador;

    private DatabaseReference mDataBase;

    private DatabaseReference mDataBaseCapitao = FirebaseDatabase.getInstance().getReference("Capitao");

    private Button mImage,enviar;

    private ImageView mMostrar;

    private EditText j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,nome_time;

    private Uri mImageUri;

    private StorageReference mStorage;

    private ArrayList<String> capitaes = new ArrayList<String>();

    private static final int GALLERY_REQUEST = 1;

    Boolean ja_foi_inserida = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_time);

        mDataBaseCapitao.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String novo = dataSnapshot.getValue(String.class);
                capitaes.add(novo);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        jogador = new ArrayList<>();

        mImage = (Button) findViewById(R.id.imagem);

        mMostrar = (ImageView) findViewById(mostrar_imagem);

        mStorage = FirebaseStorage.getInstance().getReference();

        nome_time = (EditText) findViewById(R.id.nome_time);
        j1 = (EditText) findViewById(R.id.j1);
        j2 = (EditText) findViewById(R.id.j2);
        j3 = (EditText) findViewById(R.id.j3);
        j4 = (EditText) findViewById(R.id.j4);
        j5 = (EditText) findViewById(R.id.j5);
        j6 = (EditText) findViewById(R.id.j6);
        j7 = (EditText) findViewById(R.id.j7);
        j8 = (EditText) findViewById(R.id.j8);
        j9 = (EditText) findViewById(R.id.j9);
        j10 = (EditText) findViewById(R.id.j10);
        j11 = (EditText) findViewById(R.id.j11);
        j12 = (EditText) findViewById(R.id.j12);
        j13 = (EditText) findViewById(R.id.j13);
        j14 = (EditText) findViewById(R.id.j14);
        j15 = (EditText) findViewById(R.id.j15);

        enviar = (Button) findViewById(R.id.enviar);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);

                mMostrar.setVisibility(View.VISIBLE);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user == null){
                    Toast.makeText(Criar_time.this, "Você não possui permissão para criar time.", Toast.LENGTH_SHORT).show();
                }
                else {

                    boolean achei = false;

                    for(String it: capitaes){
                        if(it.equals(user.getEmail())) achei = true;
                    }

                    if(achei) {

                        Boolean correto = armazenar();

                        Intent intent = new Intent(Criar_time.this, Times.class);

                        intent.putExtra("criar", "criado");

                        if (correto) {
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(Criar_time.this, "Você não possui permissão para criar time.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private Boolean armazenar(){

        user = Criar_usuario();

        if(user == null) return false;

        if(!ja_foi_inserida) {
            //the imageView is empty
            String img_padrao = "https://firebasestorage.googleapis.com/v0/b/computsal-70e30.appspot.com/o/Logo_times%2FlogoTime.jpeg?alt=media&token=73acb400-3682-471a-bb40-6b705feb2960";

            user.setLogo(img_padrao);

            mDataBase = FirebaseDatabase.getInstance().getReference().child("Times");

            DatabaseReference newPost = mDataBase.push();

            newPost.setValue(user);

        }
        else if(ja_foi_inserida){
            // there is an image
            StorageReference filepath = mStorage.child("Logo_times").child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests")
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    mDataBase = FirebaseDatabase.getInstance().getReference().child("Times");

                    DatabaseReference newPost = mDataBase.push();

                    user.setLogo(downloadUrl.toString());

                    newPost.setValue(user);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Criar_time.this, "Falha ao baixar imagem", Toast.LENGTH_SHORT).show();
                }
            });
        }

        mDataBase = FirebaseDatabase.getInstance().getReference().child("Jogadores");

        for(Jogador j: jogador){
            DatabaseReference newPost = mDataBase.push();
            newPost.setValue(j);
        }

        return true;

    }

    private Time Criar_usuario(){

        Time user = new Time();

        jogador.clear();

        if(TextUtils.isEmpty(nome_time.getText().toString().trim())){
            nome_time.setError("Obrigatório");
            Toast.makeText(this, "Insira o nome do time", Toast.LENGTH_SHORT).show();
            return null;
        }
        else user.setNome_time(nome_time.getText().toString().trim());

        int total_jogador = 0;

        if(TextUtils.isEmpty(j1.getText().toString().trim())) user.setJ1("#");
        else {
            user.setJ1(j1.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j1.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j2.getText().toString().trim())) user.setJ2("#");
        else {
            user.setJ2(j2.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j2.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j3.getText().toString().trim())) user.setJ3("#");
        else {
            user.setJ3(j3.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j3.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j4.getText().toString().trim())) user.setJ4("#");
        else{
            user.setJ4(j4.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j4.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j5.getText().toString().trim())) user.setJ5("#");
        else {
            user.setJ5(j5.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j5.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j6.getText().toString().trim())) user.setJ6("#");
        else {
            user.setJ6(j6.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j6.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j7.getText().toString().trim())) user.setJ7("#");
        else{
            user.setJ7(j7.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j7.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j8.getText().toString().trim())) user.setJ8("#");
        else {
            user.setJ8(j8.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j8.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j9.getText().toString().trim())) user.setJ9("#");
        else {
            user.setJ9(j9.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j9.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j10.getText().toString().trim())) user.setJ10("#");
        else {
            user.setJ10(j10.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j10.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j11.getText().toString().trim())) user.setJ11("#");
        else {
            user.setJ11(j11.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j11.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j12.getText().toString().trim())) user.setJ12("#");
        else {
            user.setJ12(j12.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j12.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j13.getText().toString().trim())) user.setJ13("#");
        else {
            user.setJ13(j13.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j13.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j14.getText().toString().trim())) user.setJ14("#");
        else {
            user.setJ14(j14.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j14.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }
        if(TextUtils.isEmpty(j15.getText().toString().trim())) user.setJ15("#");
        else {
            user.setJ15(j15.getText().toString().trim());
            total_jogador++;
            jogador.add(new Jogador(j15.getText().toString().trim(),nome_time.getText().toString().trim(),0));
        }

        if(total_jogador < 4){
            Toast.makeText(this, "Insira pelo menos 4 jogadores", Toast.LENGTH_SHORT).show();
            return null;
        }

        user.setPago(false);
        user.setVitorias(0);
        user.setDerrotas(0);
        user.setEmpates(0);
        user.setGols_feitos(0);
        user.setGols_recebidos(0);
        user.setPontos(0);

        return user;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mMostrar.setImageURI(mImageUri);
            ja_foi_inserida = true;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Criar_time.this,Times.class));
        super.onBackPressed();
    }

}