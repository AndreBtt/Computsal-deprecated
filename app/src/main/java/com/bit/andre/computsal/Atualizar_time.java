package com.bit.andre.computsal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import Model.Grupo;
import Model.Horario;
import Model.Jogador;
import Model.Jogo;
import Model.Time;

import static com.bit.andre.computsal.R.id.mostrar_imagem;
import static com.bit.andre.computsal.R.id.time;

public class Atualizar_time extends AppCompatActivity {

    private int cont = 0;
    private boolean foi = false;
    private int cont1;
    private boolean foi1;
    private int cont2;
    private boolean foi2;
    private int cont3;
    private boolean foi3;
    private int cont4;
    private boolean foi4;

    private Time time_atualizar;

    private DatabaseReference newPost;

    private String img_padrao;

    private String verificar_nome;

    private Time user;

    private ArrayList<Jogador> jogador;

    private ArrayList<Jogador> jogador_antigo;

    private ArrayList <Jogo> jogos;

    private DatabaseReference mDataBase;

    private Button mImage,enviar;

    private ImageView mMostrar;

    private EditText j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,nome_time;

    private Uri mImageUri;

    private StorageReference mStorage;

    private Grupo grupo_salva;

    private Horario horario_salva;

    private static final int GALLERY_REQUEST = 1;

    boolean ja_foi_inserida = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_criar_time);

        jogador = new ArrayList<>();

        jogador_antigo = new ArrayList<>();

        jogos = new ArrayList<>();

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

        enviar.setText("Atualizar");

        Bundle b = this.getIntent().getExtras();
        verificar_nome = b.getString("time");

        DatabaseReference DataTimes = FirebaseDatabase.getInstance().getReference("Times");

        DataTimes.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);
                if (time_atualizar == null && novo.getNome_time().equals(verificar_nome)) {
                    time_atualizar = novo;
                    atualizar_tela();
                }
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

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean correto = armazenar();

                Intent intent = new Intent(Atualizar_time.this, Times.class);

                intent.putExtra("criar", "criado");

                if (correto) {
                    startActivity(intent);
                }
            }
        });
    }

    private void atualizar_tela(){

        img_padrao = time_atualizar.getLogo();

        mImage.setText("Atualizar logo do time");

        Glide.with(Atualizar_time.this).load(img_padrao).into(mMostrar);
        mMostrar.setVisibility(View.VISIBLE);

        nome_time.setText(time_atualizar.getNome_time());
        j1.setText(time_atualizar.getJ1().equals("#") ? "" : time_atualizar.getJ1());
        j2.setText(time_atualizar.getJ2().equals("#") ? "" : time_atualizar.getJ2());
        j3.setText(time_atualizar.getJ3().equals("#") ? "" : time_atualizar.getJ3());
        j4.setText(time_atualizar.getJ4().equals("#") ? "" : time_atualizar.getJ4());
        j5.setText(time_atualizar.getJ5().equals("#") ? "" : time_atualizar.getJ5());
        j6.setText(time_atualizar.getJ6().equals("#") ? "" : time_atualizar.getJ6());
        j7.setText(time_atualizar.getJ7().equals("#") ? "" : time_atualizar.getJ7());
        j8.setText(time_atualizar.getJ8().equals("#") ? "" : time_atualizar.getJ8());
        j9.setText(time_atualizar.getJ9().equals("#") ? "" : time_atualizar.getJ9());
        j10.setText(time_atualizar.getJ10().equals("#") ? "" : time_atualizar.getJ10());
        j11.setText(time_atualizar.getJ11().equals("#") ? "" : time_atualizar.getJ11());
        j12.setText(time_atualizar.getJ12().equals("#") ? "" : time_atualizar.getJ12());
        j13.setText(time_atualizar.getJ13().equals("#") ? "" : time_atualizar.getJ13());
        j14.setText(time_atualizar.getJ14().equals("#") ? "" : time_atualizar.getJ14());
        j15.setText(time_atualizar.getJ15().equals("#") ? "" : time_atualizar.getJ15());

        if(!(TextUtils.isEmpty(j1.getText()))) cont++;
        if(!(TextUtils.isEmpty(j2.getText()))) cont++;
        if(!(TextUtils.isEmpty(j3.getText()))) cont++;
        if(!(TextUtils.isEmpty(j4.getText()))) cont++;
        if(!(TextUtils.isEmpty(j5.getText()))) cont++;
        if(!(TextUtils.isEmpty(j6.getText()))) cont++;
        if(!(TextUtils.isEmpty(j7.getText()))) cont++;
        if(!(TextUtils.isEmpty(j8.getText()))) cont++;
        if(!(TextUtils.isEmpty(j9.getText()))) cont++;
        if(!(TextUtils.isEmpty(j10.getText()))) cont++;
        if(!(TextUtils.isEmpty(j11.getText()))) cont++;
        if(!(TextUtils.isEmpty(j12.getText()))) cont++;
        if(!(TextUtils.isEmpty(j13.getText()))) cont++;
        if(!(TextUtils.isEmpty(j14.getText()))) cont++;
        if(!(TextUtils.isEmpty(j15.getText()))) cont++;

    }

    private boolean armazenar(){

        user = Criar_usuario();

        atualizar_jogadores();
        atualizar_times();
        atualizar_grupos();
        atualizar_jogos();
        atualizar_horarios();

        return true;

    }

    private void atualizar_horarios(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Horarios");

        cont4 = 1;
        foi4 = false;

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Horario novo = dataSnapshot.getValue(Horario.class);

                if(!foi4 && novo != null && novo.getTime().equals(time_atualizar.getNome_time())) {
                    cont4--;
                    horario_salva = novo;
                    horario_salva.setTime(user.getNome_time());
                    dataSnapshot.getRef().removeValue();
                }

                if(!foi4 && cont4 == 0){
                    mDataBase = FirebaseDatabase.getInstance().getReference("Horarios");
                    newPost = mDataBase.push();
                    newPost.setValue(horario_salva);
                    foi4 = true;
                }
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

    }

    private void atualizar_jogos(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Jogos");

        foi3 = false;
        cont3 = 3; // MUDA AQUI CASO A QUANTIDADE DE TIMES POR GRUPO MUDE

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogo novo = dataSnapshot.getValue(Jogo.class);

                if(!foi3 && novo != null) {
                    if (novo.getT1().equals(time_atualizar.getNome_time())){
                        novo.setT1(user.getNome_time());
                        jogos.add(novo);
                        cont3--;
                        dataSnapshot.getRef().removeValue();
                    }
                    else if (novo.getT2().equals(time_atualizar.getNome_time())) {
                        novo.setT2(user.getNome_time());
                        cont3--;
                        jogos.add(novo);
                        dataSnapshot.getRef().removeValue();
                    }
                }

                if(!foi3 && cont3 == 0){
                    mDataBase = FirebaseDatabase.getInstance().getReference("Jogos");
                    for(Jogo j: jogos) {
                        newPost = mDataBase.push();
                        newPost.setValue(j);
                    }
                    foi3 = true;
                }
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
    }

    private void atualizar_grupos(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Grupos");

        foi2 = false;
        cont2 = 1;

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grupo novo = dataSnapshot.getValue(Grupo.class);

                if(!foi2 && novo != null) {
                    if (novo.getT1().equals(time_atualizar.getNome_time())){
                        novo.setT1(user.getNome_time());
                        grupo_salva = novo;
                        cont2--;
                        dataSnapshot.getRef().removeValue();
                    }
                    else if (novo.getT2().equals(time_atualizar.getNome_time())){
                        novo.setT2(user.getNome_time());
                        cont2--;
                        grupo_salva = novo;
                        dataSnapshot.getRef().removeValue();
                    }
                    else if (novo.getT3().equals(time_atualizar.getNome_time())){
                        novo.setT3(user.getNome_time());
                        cont2--;
                        grupo_salva = novo;
                        dataSnapshot.getRef().removeValue();
                    }
                    else if (novo.getT4().equals(time_atualizar.getNome_time())){
                        novo.setT4(user.getNome_time());
                        cont2--;
                        grupo_salva = novo;
                        dataSnapshot.getRef().removeValue();
                    }
                }

                if(!foi2 && cont2 == 0){
                    mDataBase = FirebaseDatabase.getInstance().getReference("Grupos");
                    newPost = mDataBase.push();
                    newPost.setValue(grupo_salva);
                    foi2 = true;
                }
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
    }

    private void atualizar_jogadores(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Jogadores");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jogador novo = dataSnapshot.getValue(Jogador.class);

                if(!foi && novo != null && novo.getTime().equals(time_atualizar.getNome_time())) {
                    for(Jogador j: jogador){
                        if(j.getNome().equals(novo.getNome())) j.setGol(novo.getGol());
                    }
                    cont--;
                    dataSnapshot.getRef().removeValue();
                }

                if(!foi && cont == 0){
                    mDataBase = FirebaseDatabase.getInstance().getReference("Jogadores");
                    for(Jogador j: jogador){
                        newPost = mDataBase.push();
                        newPost.setValue(j);
                    }
                    foi = true;
                }
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

    }

    private void atualizar_times(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Times");

        cont1 = 1;
        foi1 = false;

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);

                if(!foi1 && novo != null && novo.getNome_time().equals(time_atualizar.getNome_time())) {
                    cont1--;
                    dataSnapshot.getRef().removeValue();
                }

                if(!foi1 && cont1 == 0){
                    mDataBase = FirebaseDatabase.getInstance().getReference("Jogadores");
                    atualizar_time();
                    foi1 = true;
                }
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
    }

    private void atualizar_time(){

        if(!ja_foi_inserida) {

            user.setLogo(img_padrao);

            mDataBase = FirebaseDatabase.getInstance().getReference().child("Times");

            newPost = mDataBase.push();

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
                    Toast.makeText(Atualizar_time.this, "Falha ao baixar imagem", Toast.LENGTH_SHORT).show();
                }
            });
        }
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

        user.setPago(time_atualizar.getPago());
        user.setVitorias(time_atualizar.getVitorias());
        user.setDerrotas(time_atualizar.getDerrotas());
        user.setEmpates(time_atualizar.getEmpates());
        user.setGols_feitos(time_atualizar.getGols_feitos());
        user.setGols_recebidos(time_atualizar.getGols_recebidos());
        user.setPontos(time_atualizar.getPontos());
        user.setEmail(time_atualizar.getEmail());

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
        startActivity(new Intent(Atualizar_time.this,Times.class));
        super.onBackPressed();
    }

}