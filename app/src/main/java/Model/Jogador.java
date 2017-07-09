package Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Jogador {

    private String nome;
    private String time;
    private int gol;


    public Jogador(){};

    public Jogador(String nome, String time, int gol) {
        this.nome = nome;
        this.time = time;
        this.gol = gol;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nome", time);
        result.put("time", nome);
        result.put("gol", gol);

        return result;
    }

}

