package Model;

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
}

