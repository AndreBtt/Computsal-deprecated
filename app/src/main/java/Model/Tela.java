package Model;

public class Tela {

    String img,txt;

    Tela(){}

    public Tela(String img, String txt) {
        this.img = img;
        this.txt = txt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
