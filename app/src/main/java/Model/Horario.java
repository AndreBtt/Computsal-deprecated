package Model;

/**
 * Created by andre on 10/10/17.
 */

public class Horario {

    private String email;
    private boolean h1,h2,h3,h4,h5,h6,h7,h8;
    private String time;

    public Horario(){};

    public Horario(String email, String time) {
        this.email = email;
        this.h1 = true;
        this.h2 = true;
        this.h3 = true;
        this.h4 = true;
        this.h5 = true;
        this.h6 = true;
        this.h7 = true;
        this.h8 = true;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isH1() {
        return h1;
    }

    public void setH1(boolean h1) {
        this.h1 = h1;
    }

    public boolean isH2() {
        return h2;
    }

    public void setH2(boolean h2) {
        this.h2 = h2;
    }

    public boolean isH3() {
        return h3;
    }

    public void setH3(boolean h3) {
        this.h3 = h3;
    }

    public boolean isH4() {
        return h4;
    }

    public void setH4(boolean h4) {
        this.h4 = h4;
    }

    public boolean isH5() {
        return h5;
    }

    public void setH5(boolean h5) {
        this.h5 = h5;
    }

    public boolean isH6() {
        return h6;
    }

    public void setH6(boolean h6) {
        this.h6 = h6;
    }

    public boolean isH7() {
        return h7;
    }

    public void setH7(boolean h7) {
        this.h7 = h7;
    }

    public boolean isH8() {
        return h8;
    }

    public void setH8(boolean h8) {
        this.h8 = h8;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
