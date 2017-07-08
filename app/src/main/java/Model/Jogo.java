package Model;

public class Jogo {

    String t1,t2;
    int g1,g2;

    Jogo(){}

    public Jogo(String t1, String t2, int g1, int g2) {
        this.t1 = t1;
        this.t2 = t2;
        this.g1 = g1;
        this.g2 = g2;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public int getG1() {
        return g1;
    }

    public void setG1(int g1) {
        this.g1 = g1;
    }

    public int getG2() {
        return g2;
    }

    public void setG2(int g2) {
        this.g2 = g2;
    }
}
