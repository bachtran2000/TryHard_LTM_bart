package D5.B2;

public class Book {
    protected String id,ten,nxb;
    protected int sl, slm;

    public Book() {
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

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getSlm() {
        return slm;
    }

    public void setSlm(int slm) {
        this.slm = slm;
    }

    @Override
    public String toString() {
        return
                "id='" + id + '\'' +
                ", ten='" + ten + '\'' +
                ", nxb='" + nxb + '\'' +
                ", sl=" + sl +
                ", slm=" + slm ;
    }

    public String toShow() {
        return
                        "$" + id + '\'' +
                        "$" + ten + '\'' +
                        "$" + nxb + '\'' +
                        "$" + sl +
                        "$" + slm ;
    }

}
