package D6.B2;

public class matHang {
    protected String id,ten;
    protected float gb;
    protected int sltk;

    public matHang() {
    }

    public matHang(String id, String ten, float gb, int sltk) {
        this.id = id;
        this.ten = ten;
        this.gb = gb;
        this.sltk = sltk;
    }

    @Override
    public String toString() {
        return id + "$" + ten +  "$" + gb +"$" + sltk;
    }

    public String toShow(){
        return "Ma hang: "+id +"| Ten hang: "+ten+"| Gia ban: "+gb+"| So luong ton kho: "+sltk;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getGb() {
        return gb;
    }

    public void setGb(float gb) {
        this.gb = gb;
    }

    public int getSltk() {
        return sltk;
    }

    public void setSltk(int sltk) {
        this.sltk = sltk;
    }
}
