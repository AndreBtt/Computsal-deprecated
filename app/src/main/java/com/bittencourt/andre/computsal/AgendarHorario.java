package com.bittencourt.andre.computsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.Horario;

public class AgendarHorario extends AppCompatActivity {

    DatabaseReference mBanco = FirebaseDatabase.getInstance().getReference("Horarios");

    private CheckBox h1;
    private CheckBox h2;
    private CheckBox h3;
    private CheckBox h4;
    private CheckBox h5;
    private CheckBox h6;
    private CheckBox h7;

    private boolean deletei = false;

    private Button enviar;

    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

    private boolean encontrou = false;

    private Horario atual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_agendar_horario);

        h1 = (CheckBox) findViewById(R.id.horario_12);
        h2 = (CheckBox) findViewById(R.id.horario_13);
        h3 = (CheckBox) findViewById(R.id.horario_14);
        h4 = (CheckBox) findViewById(R.id.horario_15);
        h5 = (CheckBox) findViewById(R.id.horario_16);
        h6 = (CheckBox) findViewById(R.id.horario_17);
        h7 = (CheckBox) findViewById(R.id.horario_18);
        enviar = (Button) findViewById(R.id.enviar);

        if(usuario != null) {
            mBanco.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Horario novo = dataSnapshot.getValue(Horario.class);
                    if (novo.getEmail().equals(usuario.getEmail())) {
                        encontrou = true;
                        atual = novo;
                        h1.setChecked(novo.isH1());
                        h2.setChecked(novo.isH2());
                        h3.setChecked(novo.isH3());
                        h4.setChecked(novo.isH4());
                        h5.setChecked(novo.isH5());
                        h6.setChecked(novo.isH6());
                        h7.setChecked(novo.isH7());
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

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(encontrou) {
                    atual.setH1(h1.isChecked());
                    atual.setH2(h2.isChecked());
                    atual.setH3(h3.isChecked());
                    atual.setH4(h4.isChecked());
                    atual.setH5(h5.isChecked());
                    atual.setH6(h6.isChecked());
                    atual.setH7(h7.isChecked());

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query query = ref.child("Horarios").orderByChild("email").equalTo(usuario.getEmail());


                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot q: dataSnapshot.getChildren()) {
                                if(!deletei) q.getRef().removeValue();
                                deletei = true;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                    ref = ref.child("Horarios").push();

                    ref.setValue(atual);

                    startActivity(new Intent(AgendarHorario.this,Principal.class));

                }
                else Toast.makeText(AgendarHorario.this, "Você não possui permissão para mudar horários.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AgendarHorario.this,Principal.class));
        super.onBackPressed();
    }

}
