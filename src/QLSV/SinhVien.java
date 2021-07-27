package QLSV;

public class SinhVien {
    protected String ht, ns, msv, dtb;

    public SinhVien() {
    }

    public SinhVien(String ht, String ns, String msv, String dtb) {
        this.ht = ht;
        this.ns = ns;
        this.msv = msv;
        this.dtb = dtb;
    }

    public String getHt() {
        return ht;
    }

    public void setHt(String ht) {
        this.ht = ht;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getDtb() {
        return dtb;
    }

    public void setDtb(String dtb) {
        this.dtb = dtb;
    }

    @Override
    public String toString() {
        return "Ho ten:'" + ht + '\'' + ", Ngay sinh:'" + ns + '\'' + ", MSV:'" + msv + '\'' + ", Diem trung binh:'" + dtb;
    }

    public String writeFile() {
        return ht + "$" + ns + "$" + msv + "$" + dtb;
    }
}
