package DAO;

import java.util.ArrayList;

import Model.Time;

public final class Ler_times {

    public static ArrayList<Time> extrair_time(){

        final ArrayList<Time> times = new ArrayList<>();

        /*final String nome;

        DatabaseReference mBanco;

        mBanco = FirebaseDatabase.getInstance().getReference("Times");

        mBanco.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               nome = dataSnapshot.getValue("nome_time");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for(int i = 0; i < times.size(); i++){
        }*/

        Time aux = new Time("https://firebasestorage.googleapis.com/v0/b/computsal-70e30.appspot.com/o/Logo_times%2Fimage%3A24313?alt=media&token=e5e6c3c7-b29b-4705-8328-32ca7e93a67e","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","gol++",false);
        Time aux1 = new Time("https://firebasestorage.googleapis.com/v0/b/computsal-70e30.appspot.com/o/Logo_times%2Fimage%3A24313?alt=media&token=e5e6c3c7-b29b-4705-8328-32ca7e93a67e","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","gol++",false);

        times.add(aux);
        times.add(aux1);

        return times;
    }

}
