package com.example.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Jogador;
import Model.Jogo;
import Model.Time;

public class Jogo_config extends AppCompatActivity {

    private ImageView mImgt1;
    private ImageView mImgt2;

    private TextView mNomet1;
    private TextView mNomet2;

    private TextView mGolt1;
    private TextView mGolt2;

    private TextView mNomej1_t1;
    private Button maddj1_t1;

    private TextView mNomej2_t1;
    private Button maddj2_t1;

    private TextView mNomej3_t1;
    private Button maddj3_t1;

    private TextView mNomej4_t1;
    private Button maddj4_t1;

    private TextView mNomej5_t1;
    private Button maddj5_t1;

    private TextView mNomej6_t1;
    private Button maddj6_t1;

    private TextView mNomej7_t1;
    private Button maddj7_t1;

    private TextView mNomej8_t1;
    private Button maddj8_t1;

    private TextView mNomej9_t1;
    private Button maddj9_t1;

    private TextView mNomej10_t1;
    private Button maddj10_t1;

    private TextView mNomej11_t1;
    private Button maddj11_t1;

    private TextView mNomej12_t1;
    private Button maddj12_t1;

    private TextView mNomej13_t1;
    private Button maddj13_t1;

    private TextView mNomej14_t1;
    private Button maddj14_t1;

    private TextView mNomej15_t1;
    private Button maddj15_t1;


    private TextView mNomej1_t2;
    private Button maddj1_t2;

    private TextView mNomej2_t2;
    private Button maddj2_t2;

    private TextView mNomej3_t2;
    private Button maddj3_t2;

    private TextView mNomej4_t2;
    private Button maddj4_t2;

    private TextView mNomej5_t2;
    private Button maddj5_t2;

    private TextView mNomej6_t2;
    private Button maddj6_t2;

    private TextView mNomej7_t2;
    private Button maddj7_t2;

    private TextView mNomej8_t2;
    private Button maddj8_t2;

    private TextView mNomej9_t2;
    private Button maddj9_t2;

    private TextView mNomej10_t2;
    private Button maddj10_t2;

    private TextView mNomej11_t2;
    private Button maddj11_t2;

    private TextView mNomej12_t2;
    private Button maddj12_t2;

    private TextView mNomej13_t2;
    private Button maddj13_t2;

    private TextView mNomej14_t2;
    private Button maddj14_t2;

    private TextView mNomej15_t2;
    private Button maddj15_t2;

    private Button mEnviar;

    private String gol1,gol2,time1,time2;

    private DatabaseReference Banco;

    private Time t1;
    private Time t2;

    private ArrayList<String> jogadores_t1 = new ArrayList<String>();
    private ArrayList<String> jogadores_t2 = new ArrayList<String>();

    private ArrayList<Jogador> mJogadores_t1 = new ArrayList<Jogador>();
    private ArrayList<Jogador> mJogadores_t2 = new ArrayList<Jogador>();

    private int cont;
    private int cont1 = 0;
    private int cont2 = 0;

    private HashMap<String,Integer> mapa_gols = new HashMap<String,Integer>();

    private String chave_jogo;


    private int mGol1;
    private int mGol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_jogo_config);

        mImgt1 = (ImageView) findViewById(R.id.logo_t1);
        mImgt2 = (ImageView) findViewById(R.id.logo_t2);

        mNomet1 = (TextView) findViewById(R.id.nome_t1);
        mNomet2 = (TextView) findViewById(R.id.nome_t2);

        mGolt1 = (TextView) findViewById(R.id.t1_gol);
        mGolt2 = (TextView) findViewById(R.id.t2_gol);

        mNomej1_t1 = (TextView) findViewById(R.id.t1_j1);
        maddj1_t1 = (Button) findViewById(R.id.addt1_j1);

        mNomej2_t1 = (TextView) findViewById(R.id.t1_j2);
        maddj2_t1 = (Button) findViewById(R.id.addt1_j2);

        mNomej3_t1 = (TextView) findViewById(R.id.t1_j3);
        maddj3_t1 = (Button) findViewById(R.id.addt1_j3);

        mNomej4_t1 = (TextView) findViewById(R.id.t1_j4);
        maddj4_t1 = (Button) findViewById(R.id.addt1_j4);

        mNomej5_t1 = (TextView) findViewById(R.id.t1_j5);
        maddj5_t1 = (Button) findViewById(R.id.addt1_j5);

        mNomej6_t1 = (TextView) findViewById(R.id.t1_j6);
        maddj6_t1 = (Button) findViewById(R.id.addt1_j6);

        mNomej7_t1 = (TextView) findViewById(R.id.t1_j7);
        maddj7_t1 = (Button) findViewById(R.id.addt1_j7);

        mNomej8_t1 = (TextView) findViewById(R.id.t1_j8);
        maddj8_t1 = (Button) findViewById(R.id.addt1_j8);

        mNomej9_t1 = (TextView) findViewById(R.id.t1_j9);
        maddj9_t1 = (Button) findViewById(R.id.addt1_j9);

        mNomej10_t1 =(TextView) findViewById(R.id.t1_j10);
        maddj10_t1 =(Button) findViewById(R.id.addt1_j10);

        mNomej11_t1 =(TextView) findViewById(R.id.t1_j11);
        maddj11_t1 =(Button) findViewById(R.id.addt1_j11);

        mNomej12_t1 =(TextView) findViewById(R.id.t1_j12);
        maddj12_t1 =(Button) findViewById(R.id.addt1_j12);

        mNomej13_t1 =(TextView) findViewById(R.id.t1_j13);
        maddj13_t1 =(Button) findViewById(R.id.addt1_j13);

        mNomej14_t1 =(TextView) findViewById(R.id.t1_j14);
        maddj14_t1 =(Button) findViewById(R.id.addt1_j14);

        mNomej15_t1 =(TextView) findViewById(R.id.t1_j15);
        maddj15_t1 =(Button) findViewById(R.id.addt1_j15);

        mNomej1_t2 = (TextView) findViewById(R.id.t2_j1);
        maddj1_t2 = (Button) findViewById(R.id.addt2_j1);

        mNomej2_t2 = (TextView) findViewById(R.id.t2_j2);
        maddj2_t2 = (Button) findViewById(R.id.addt2_j2);

        mNomej3_t2 = (TextView) findViewById(R.id.t2_j3);
        maddj3_t2 = (Button) findViewById(R.id.addt2_j3);

        mNomej4_t2 = (TextView) findViewById(R.id.t2_j4);
        maddj4_t2 = (Button) findViewById(R.id.addt2_j4);

        mNomej5_t2 = (TextView) findViewById(R.id.t2_j5);
        maddj5_t2 = (Button) findViewById(R.id.addt2_j5);

        mNomej6_t2 = (TextView) findViewById(R.id.t2_j6);
        maddj6_t2 = (Button) findViewById(R.id.addt2_j6);

        mNomej7_t2 = (TextView) findViewById(R.id.t2_j7);
        maddj7_t2 = (Button) findViewById(R.id.addt2_j7);

        mNomej8_t2 = (TextView) findViewById(R.id.t2_j8);
        maddj8_t2 = (Button) findViewById(R.id.addt2_j8);

        mNomej9_t2 = (TextView) findViewById(R.id.t2_j9);
        maddj9_t2 = (Button) findViewById(R.id.addt2_j9);

        mNomej10_t2 =(TextView) findViewById(R.id.t2_j10);
        maddj10_t2 =(Button) findViewById(R.id.addt2_j10);

        mNomej11_t2 =(TextView) findViewById(R.id.t2_j11);
        maddj11_t2 =(Button) findViewById(R.id.addt2_j11);

        mNomej12_t2 =(TextView) findViewById(R.id.t2_j12);
        maddj12_t2 =(Button) findViewById(R.id.addt2_j12);

        mNomej13_t2 =(TextView) findViewById(R.id.t2_j13);
        maddj13_t2 =(Button) findViewById(R.id.addt2_j13);

        mNomej14_t2 =(TextView) findViewById(R.id.t2_j14);
        maddj14_t2 =(Button) findViewById(R.id.addt2_j14);

        mNomej15_t2 =(TextView) findViewById(R.id.t2_j15);
        maddj15_t2 =(Button) findViewById(R.id.addt2_j15);

        mEnviar = (Button) findViewById(R.id.enviar);

        Bundle b = this.getIntent().getExtras();
        if(b != null){
            time1 = b.getString("t1");
            time2 = b.getString("t2");
            gol1 = b.getString("g1");
            gol2 = b.getString("g2");
        }

        mGol1 = Integer.parseInt(gol1);
        mGol2 = Integer.parseInt(gol2);

        Banco = FirebaseDatabase.getInstance().getReference("Times");

        cont = 0;

        Banco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);

                if(novo.getNome_time().equals(time1)){
                    t1 = novo;
                    criar_jogadores1();
                    cont++;
                }
                else if(novo.getNome_time().equals(time2)){
                    t2 = novo;
                    criar_jogadores2();
                    cont++;
                }

                if(cont == 2){
                    criarView();
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

        maddj1_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 0) {
                    int gol = mJogadores_t1.get(0).getGol();
                    gol++;
                    mJogadores_t1.get(0).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });

        maddj2_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 1) {
                    int gol = mJogadores_t1.get(1).getGol();
                    gol++;
                    mJogadores_t1.get(1).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj3_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 2) {
                    int gol = mJogadores_t1.get(2).getGol();
                    gol++;
                    mJogadores_t1.get(2).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj4_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 3) {
                    int gol = mJogadores_t1.get(3).getGol();
                    gol++;
                    mJogadores_t1.get(3).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj5_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 4) {
                    int gol = mJogadores_t1.get(4).getGol();
                    gol++;
                    mJogadores_t1.get(4).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj6_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 5) {
                    int gol = mJogadores_t1.get(5).getGol();
                    gol++;
                    mJogadores_t1.get(5).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj7_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 6) {
                    int gol = mJogadores_t1.get(6).getGol();
                    gol++;
                    mJogadores_t1.get(6).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj8_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 7) {
                    int gol = mJogadores_t1.get(7).getGol();
                    gol++;
                    mJogadores_t1.get(7).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj9_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 8) {
                    int gol = mJogadores_t1.get(8).getGol();
                    gol++;
                    mJogadores_t1.get(8).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj10_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 9) {
                    int gol = mJogadores_t1.get(9).getGol();
                    gol++;
                    mJogadores_t1.get(9).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj11_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 10) {
                    int gol = mJogadores_t1.get(10).getGol();
                    gol++;
                    mJogadores_t1.get(10).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj12_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 11) {
                    int gol = mJogadores_t1.get(11).getGol();
                    gol++;
                    mJogadores_t1.get(11).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj13_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 12) {
                    int gol = mJogadores_t1.get(12).getGol();
                    gol++;
                    mJogadores_t1.get(12).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj14_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 13) {
                    int gol = mJogadores_t1.get(13).getGol();
                    gol++;
                    mJogadores_t1.get(13).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });
        maddj15_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t1.size() > 14) {
                    int gol = mJogadores_t1.get(14).getGol();
                    gol++;
                    mJogadores_t1.get(14).setGol(gol);
                    mGol1++;
                    ajustaGol();
                }
            }
        });


        maddj1_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 0) {
                    int gol = mJogadores_t2.get(0).getGol();
                    gol++;
                    mJogadores_t2.get(0).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });

        maddj2_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 1) {
                    int gol = mJogadores_t2.get(1).getGol();
                    gol++;
                    mJogadores_t2.get(1).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj3_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 2) {
                    int gol = mJogadores_t2.get(2).getGol();
                    gol++;
                    mJogadores_t2.get(2).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj4_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 3) {
                    int gol = mJogadores_t2.get(3).getGol();
                    gol++;
                    mJogadores_t2.get(3).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj5_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 4) {
                    int gol = mJogadores_t2.get(4).getGol();
                    gol++;
                    mJogadores_t2.get(4).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj6_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 5) {
                    int gol = mJogadores_t2.get(5).getGol();
                    gol++;
                    mJogadores_t2.get(5).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj7_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 6) {
                    int gol = mJogadores_t2.get(6).getGol();
                    gol++;
                    mJogadores_t2.get(6).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj8_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 7) {
                    int gol = mJogadores_t2.get(7).getGol();
                    gol++;
                    mJogadores_t2.get(7).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj9_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 8) {
                    int gol = mJogadores_t2.get(8).getGol();
                    gol++;
                    mJogadores_t2.get(8).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj10_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 9) {
                    int gol = mJogadores_t2.get(9).getGol();
                    gol++;
                    mJogadores_t2.get(9).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj11_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 10) {
                    int gol = mJogadores_t2.get(10).getGol();
                    gol++;
                    mJogadores_t2.get(10).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj12_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 11) {
                    int gol = mJogadores_t2.get(11).getGol();
                    gol++;
                    mJogadores_t2.get(11).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj13_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 12) {
                    int gol = mJogadores_t2.get(12).getGol();
                    gol++;
                    mJogadores_t2.get(12).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj14_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 13) {
                    int gol = mJogadores_t2.get(13).getGol();
                    gol++;
                    mJogadores_t2.get(13).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });
        maddj15_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mJogadores_t2.size() > 14) {
                    int gol = mJogadores_t2.get(14).getGol();
                    gol++;
                    mJogadores_t2.get(14).setGol(gol);
                    mGol2++;
                    ajustaGol();
                }
            }
        });

        mEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Banco = FirebaseDatabase.getInstance().getReference("Jogadores");

                final ArrayList<String> chaves = new ArrayList<String>();

                Banco.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Jogador novo = dataSnapshot.getValue(Jogador.class);

                        if((novo.getTime().equals(time1) || novo.getTime().equals(time2))){
                            if(chaves.size() < (mJogadores_t1.size() + mJogadores_t2.size())) {
                                mapa_gols.put(novo.getNome()+novo.getTime(),novo.getGol());
                                chaves.add(dataSnapshot.getKey());
                                cont1++;
                            }
                        }

                        if(cont1 == (mJogadores_t1.size() + mJogadores_t2.size())){

                            for(String x: chaves){
                                String del = "Jogadores/" + x;
                                DatabaseReference deletar = FirebaseDatabase.getInstance().getReference(del);
                                deletar.removeValue();
                            }

                            for(Jogador j: mJogadores_t1){
                                if(cont != -1) {
                                    String comp = j.getNome()+j.getTime();
                                    int gol_atual = j.getGol();
                                    gol_atual += mapa_gols.get(comp);
                                    j.setGol(gol_atual);
                                    DatabaseReference newPost = Banco.push();
                                    newPost.setValue(j);
                                }
                            }

                            for(Jogador j: mJogadores_t2){
                                if(cont != -1) {
                                    String comp = j.getNome()+j.getTime();
                                    int gol_atual = j.getGol();
                                    gol_atual += mapa_gols.get(comp);
                                    j.setGol(gol_atual);
                                    DatabaseReference newPost = Banco.push();
                                    newPost.setValue(j);
                                }
                            }

                            cont1 = -1;
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

                final DatabaseReference Banco1 = FirebaseDatabase.getInstance().getReference("Jogos");

                Jogo j = new Jogo(time1,time2,mGol1,mGol2,true);

                Banco1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Jogo novo = dataSnapshot.getValue(Jogo.class);

                        if((novo.getT1().equals(time1) && (novo.getT2().equals(time2)))){
                            if(cont2 != -1) chave_jogo = dataSnapshot.getKey();
                        }
                        else if((novo.getT1().equals(time2) && (novo.getT2().equals(time1)))){
                            if(cont2 != -1) chave_jogo = dataSnapshot.getKey();
                        }

                        if(chave_jogo != null && !chave_jogo.isEmpty()){

                            if(cont2 != -1){
                                String del = "Jogos/" + chave_jogo;
                                DatabaseReference deletar = FirebaseDatabase.getInstance().getReference(del);
                                deletar.removeValue();

                                Jogo j = new Jogo(time1,time2,mGol1,mGol2,true);

                                DatabaseReference newPost = Banco1.push();

                                newPost.setValue(j);
                            }

                            cont2 = -1;
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

                // DELETAR TIMES E ADD NOVOS TIMES

                startActivity(new Intent(Jogo_config.this,Todos_jogos.class));

            }
        });

    }

    private void ajustaGol(){

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(mGol1);
        String g1 = sb.toString();

        sb = new StringBuilder();
        sb.append("");
        sb.append(mGol2);
        String g2 = sb.toString();

        mGolt1.setText(g1);
        mGolt2.setText(g2);
    }

    private void criar_jogadores1(){
        Glide.with(this).load(t1.getLogo()).into(mImgt1);
        if(!t1.getJ1().equals("#")) jogadores_t1.add(t1.getJ1());
        if(!t1.getJ2().equals("#")) jogadores_t1.add(t1.getJ2());
        if(!t1.getJ3().equals("#")) jogadores_t1.add(t1.getJ3());
        if(!t1.getJ4().equals("#")) jogadores_t1.add(t1.getJ4());
        if(!t1.getJ5().equals("#")) jogadores_t1.add(t1.getJ5());
        if(!t1.getJ6().equals("#")) jogadores_t1.add(t1.getJ6());
        if(!t1.getJ7().equals("#")) jogadores_t1.add(t1.getJ7());
        if(!t1.getJ8().equals("#")) jogadores_t1.add(t1.getJ8());
        if(!t1.getJ9().equals("#")) jogadores_t1.add(t1.getJ9());
        if(!t1.getJ10().equals("#")) jogadores_t1.add(t1.getJ10());
        if(!t1.getJ11().equals("#")) jogadores_t1.add(t1.getJ11());
        if(!t1.getJ12().equals("#")) jogadores_t1.add(t1.getJ12());
        if(!t1.getJ13().equals("#")) jogadores_t1.add(t1.getJ13());
        if(!t1.getJ14().equals("#")) jogadores_t1.add(t1.getJ14());
        if(!t1.getJ15().equals("#")) jogadores_t1.add(t1.getJ15());
    }

    private void criar_jogadores2(){
        Glide.with(this).load(t2.getLogo()).into(mImgt2);
        if(!t2.getJ1().equals("#")) jogadores_t2.add(t2.getJ1());
        if(!t2.getJ2().equals("#")) jogadores_t2.add(t2.getJ2());
        if(!t2.getJ3().equals("#")) jogadores_t2.add(t2.getJ3());
        if(!t2.getJ4().equals("#")) jogadores_t2.add(t2.getJ4());
        if(!t2.getJ5().equals("#")) jogadores_t2.add(t2.getJ5());
        if(!t2.getJ6().equals("#")) jogadores_t2.add(t2.getJ6());
        if(!t2.getJ7().equals("#")) jogadores_t2.add(t2.getJ7());
        if(!t2.getJ8().equals("#")) jogadores_t2.add(t2.getJ8());
        if(!t2.getJ9().equals("#")) jogadores_t2.add(t2.getJ9());
        if(!t2.getJ10().equals("#")) jogadores_t2.add(t2.getJ10());
        if(!t2.getJ11().equals("#")) jogadores_t2.add(t2.getJ11());
        if(!t2.getJ12().equals("#")) jogadores_t2.add(t2.getJ12());
        if(!t2.getJ13().equals("#")) jogadores_t2.add(t2.getJ13());
        if(!t2.getJ14().equals("#")) jogadores_t2.add(t2.getJ14());
        if(!t2.getJ15().equals("#")) jogadores_t2.add(t2.getJ15());
    }

    private void criarView(){

        mJogadores_t1.clear();
        mJogadores_t2.clear();

        mNomet1.setText(time1);
        mNomet2.setText(time2);

        mGolt1.setText(gol1);
        mGolt2.setText(gol2);

        int i = 0;

        if(i < jogadores_t1.size()){
            mNomej1_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej2_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej3_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej4_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej5_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej6_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej7_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej8_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej9_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej10_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej11_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej12_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej13_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej14_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        if(i < jogadores_t1.size()){
            mNomej15_t1.setText(jogadores_t1.get(i));
            mJogadores_t1.add(new Jogador(jogadores_t1.get(i),time1,0));
            i++;
        }

        i = 0;

        if(i < jogadores_t2.size()){
            mNomej1_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej2_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej3_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej4_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej5_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej6_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej7_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej8_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej9_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej10_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej11_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej12_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej13_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej14_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }

        if(i < jogadores_t2.size()){
            mNomej15_t2.setText(jogadores_t2.get(i));
            mJogadores_t2.add(new Jogador(jogadores_t2.get(i),time2,0));
            i++;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Jogo_config.this,Todos_jogos.class));
        super.onBackPressed();
    }

}
