package D9.B2;

public class Sach {
    protected String id,ten,nm;

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

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public Sach() {
    }

    public Sach(String id, String ten, String nm) {
        this.id = id;
        this.ten = ten;
        this.nm = nm;
    }

    @Override
    public String toString() {
        return id + "$"+ ten + "$"+ nm ;
    }
}
