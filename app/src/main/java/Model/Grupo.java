package Model;

public class Grupo {

    String t1,t2,t3,t4;

    String letra_grupo;

    public Grupo (){}

    public Grupo(String t1, String t2, String t3, String t4, String letra_grupo) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.letra_grupo = letra_grupo;
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

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getLetra_grupo() {
        return letra_grupo;
    }

    public void setLetra_grupo(String letra_grupo) {
        this.letra_grupo = letra_grupo;
    }
}
