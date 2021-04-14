package D8.B2;

public class matHang_RMI {
    protected String id, name;
    protected float gia;

    public matHang_RMI(String id, String name, float gia) {
        this.id = id;
        this.name = name;
        this.gia = gia;
    }

    public matHang_RMI() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
}
