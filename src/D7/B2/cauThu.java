package D7.B2;

public class cauThu {
    protected String id, ten, ns, vt;
    protected float lmd;

    @Override
    public String toString() {
        return
                id + "$" + ten + "$" + ns + "$" + vt + "$" + lmd;
    }


    public String toShow() {
        return
                "id='" + id + '\'' +
                        ", ten='" + ten + '\'' +
                        ", ns='" + ns + '\'' +
                        ", vt='" + vt + '\'' +
                        ", lmd=" + lmd;
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

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }

    public float getLmd() {
        return lmd;
    }

    public void setLmd(float lmd) {
        this.lmd = lmd;
    }

    public cauThu(String id, String ten, String ns, String vt, float lmd) {
        this.id = id;
        this.ten = ten;
        this.ns = ns;
        this.vt = vt;
        this.lmd = lmd;
    }

    public cauThu() {
    }
}
