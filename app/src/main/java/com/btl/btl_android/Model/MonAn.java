package com.btl.btl_android.Model;

public class MonAn {
    private String Mamon;
    private String TenMon;
    private String Loai;
    private String NguoiDang;
    private String CongThuc;
    private  byte[] HinhAnh;

    public String getNguoiDang() {
        return NguoiDang;
    }

    public void setNguoiDang(String nguoiDang) {
        NguoiDang = nguoiDang;
    }


    public MonAn() {
    }
//
    public MonAn(String mamon, String tenMon, String loai,String iddang, String congThuc, byte[] hinhAnh) {
        Mamon = mamon;
       TenMon = tenMon;
       Loai = loai;
        CongThuc = congThuc;
       HinhAnh = hinhAnh;
       NguoiDang = iddang;
    }

    public String getMamon() {
        return Mamon;
    }

    public void setMamon(String mamon) {
        Mamon = mamon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getCongThuc() {
        return CongThuc;
    }

    public void setCongThuc(String congThuc) {
        CongThuc = congThuc;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
