package com.bit.andre.computsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Model.Time;

public class Tabelas extends AppCompatActivity {

    private TextView nome_t1;
    private TextView nome_t2;
    private TextView nome_t3;
    private TextView nome_t4;

    private TextView v1;
    private TextView v2;
    private TextView v3;
    private TextView v4;

    private TextView d1;
    private TextView d2;
    private TextView d3;
    private TextView d4;

    private TextView e1;
    private TextView e2;
    private TextView e3;
    private TextView e4;

    private TextView gp1;
    private TextView gp2;
    private TextView gp3;
    private TextView gp4;

    private TextView gc1;
    private TextView gc2;
    private TextView gc3;
    private TextView gc4;

    private TextView pt1;
    private TextView pt2;
    private TextView pt3;
    private TextView pt4;

    private String t1,t2,t3,t4;

    private DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Times");

    private List<Time> times = new ArrayList<Time>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_tabelas);


        nome_t1 = (TextView) findViewById(R.id.nome_t1);
        nome_t2 = (TextView) findViewById(R.id.nome_t2);
        nome_t3 = (TextView) findViewById(R.id.nome_t3);
        nome_t4 = (TextView) findViewById(R.id.nome_t4);

        v1 = (TextView) findViewById(R.id.v1);
        v2 = (TextView) findViewById(R.id.v2);
        v3 = (TextView) findViewById(R.id.v3);
        v4 = (TextView) findViewById(R.id.v4);

        d1 = (TextView) findViewById(R.id.d1);
        d2 = (TextView) findViewById(R.id.d2);
        d3 = (TextView) findViewById(R.id.d3);
        d4 = (TextView) findViewById(R.id.d4);

        e1 = (TextView) findViewById(R.id.e1);
        e2 = (TextView) findViewById(R.id.e2);
        e3 = (TextView) findViewById(R.id.e3);
        e4 = (TextView) findViewById(R.id.e4);

        gp1 = (TextView) findViewById(R.id.gp1);
        gp2 = (TextView) findViewById(R.id.gp2);
        gp3 = (TextView) findViewById(R.id.gp3);
        gp4 = (TextView) findViewById(R.id.gp4);

        gc1 = (TextView) findViewById(R.id.gc1);
        gc2 = (TextView) findViewById(R.id.gc2);
        gc3 = (TextView) findViewById(R.id.gc3);
        gc4 = (TextView) findViewById(R.id.gc4);

        pt1 = (TextView) findViewById(R.id.pt1);
        pt2 = (TextView) findViewById(R.id.pt2);
        pt3 = (TextView) findViewById(R.id.pt3);
        pt4 = (TextView) findViewById(R.id.pt4);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            t1 = extras.getString("time1");
            t2 = extras.getString("time2");
            t3 = extras.getString("time3");
            t4 = extras.getString("time4");
        }

        mBanco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Time novo = dataSnapshot.getValue(Time.class);
                if(novo.getNome_time().equals(t1) || novo.getNome_time().equals(t2) ||novo.getNome_time().equals(t3) ||novo.getNome_time().equals(t4)){
                    times.add(novo);
                }

                if(times.size() == 4){
                    Collections.sort(times, new Comparator<Time>() {
                        public int compare(Time c1, Time c2) {
                            if (c1.getPontos() > c2.getPontos()) return -1;
                            else if (c1.getPontos() < c2.getPontos()) return 1;
                            else{
                                if(c1.getVitorias() > c2.getVitorias()) return -1;
                                else if(c1.getVitorias() < c2.getVitorias()) return 1;
                                else {
                                    if(c1.getEmpates() > c2.getEmpates()) return -1;
                                    else if(c1.getEmpates() < c2.getEmpates()) return 1;
                                    else{
                                        if(c1.getGols_feitos() > c2.getGols_feitos()) return -1;
                                        else if(c1.getGols_feitos() < c2.getGols_feitos()) return 1;
                                        else{
                                            if(c1.getGols_recebidos() < c2.getGols_recebidos()) return -1;
                                            else if(c1.getGols_recebidos() > c2.getGols_recebidos()) return 1;
                                            else return 0;

                                        }
                                    }
                                }
                            }
                        }});

                    nome_t1.setText(times.get(0).getNome_time());

                    StringBuilder ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(0).getVitorias());
                    v1.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(0).getDerrotas());
                    d1.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(0).getEmpates());
                    e1.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(0).getGols_feitos());
                    gp1.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(0).getGols_recebidos());
                    gc1.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(0).getPontos());
                    pt1.setText(ss.toString());

                    nome_t2.setText(times.get(1).getNome_time());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(1).getVitorias());
                    v2.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(1).getDerrotas());
                    d2.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(1).getEmpates());
                    e2.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(1).getGols_feitos());
                    gp2.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(1).getGols_recebidos());
                    gc2.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(1).getPontos());
                    pt2.setText(ss.toString());

                    nome_t3.setText(times.get(2).getNome_time());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(2).getVitorias());
                    v3.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(2).getDerrotas());
                    d3.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(2).getEmpates());
                    e3.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(2).getGols_feitos());
                    gp3.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(2).getGols_recebidos());
                    gc3.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(2).getPontos());
                    pt3.setText(ss.toString());

                    nome_t4.setText(times.get(3).getNome_time());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(3).getVitorias());
                    v4.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(3).getDerrotas());
                    d4.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(3).getEmpates());
                    e4.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(3).getGols_feitos());
                    gp4.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(3).getGols_recebidos());
                    gc4.setText(ss.toString());

                    ss = new StringBuilder();
                    ss.append("");
                    ss.append(times.get(3).getPontos());
                    pt4.setText(ss.toString());

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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Tabelas.this,Grupos_tabela.class));
        super.onBackPressed();
    }

}
