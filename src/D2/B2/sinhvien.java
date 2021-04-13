package D2.B2;

import java.io.Serializable;

public class sinhvien implements Serializable {
    protected String ten, ngaysinh, quequan, maSV;

    public sinhvien(String ten, String ngaysinh, String quequan, String maSV) {
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.maSV = maSV;
    }


    public sinhvien() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }
    public String getNamsinh(){
        String[] result = new String[3];
       result = ngaysinh.split("\\/");
       String Namsinh =result[2];
       return Namsinh;
    }
    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    @Override
    public String toString() {
        return "Ten: " +ten + "| Ngay sinh: " + ngaysinh +"| Ma Sv: " + maSV +"| Que quan: "+ quequan;
    }
    public String toFile() {
        return  ten + "$" + ngaysinh +"$" + maSV +"$"+ quequan;
    }
}


