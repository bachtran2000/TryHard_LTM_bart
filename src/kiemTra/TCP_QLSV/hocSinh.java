package kiemTra.TCP_QLSV;
public class hocSinh {
    protected String MHS, ten,diaChi,gt;
    protected float DTK;

    public hocSinh() {
    }

    public hocSinh(String MHS, String ten, String diaChi, String gt, float DTK) {
        this.MHS = MHS;
        this.ten = ten;
        this.diaChi = diaChi;
        this.gt = gt;
        this.DTK = DTK;
    }

    public String getMHS() {
        return MHS;
    }

    public void setMHS(String MHS) {
        this.MHS = MHS;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public float getDTK() {
        return DTK;
    }

    public void setDTK(float DTK) {
        this.DTK = DTK;
    }

    @Override
    public String toString() {
        return MHS+
                "$" + ten +
                "$" + diaChi +
                "$" + gt +
                "$"  + DTK ;
    }

    public String toShow(){
        return
                "Ma Hoc Sinh: " + MHS + " Ten: " + ten + " Dia Chi: " + diaChi + " Gioi tinh: " + gt + " DTK: " + DTK;
    }
}
