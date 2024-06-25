package com.btl.btl_android.Model;

public class DanhGia {
    private String mamon;
    private String idnguoidanhgia;
    private String danhgia;

    public DanhGia(String mamon, String idnguoidanhgia, String danhgia) {
        this.mamon = mamon;
        this.idnguoidanhgia = idnguoidanhgia;
        this.danhgia = danhgia;
    }

    public DanhGia() {
    }



    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public String getIdnguoidanhgia() {
        return idnguoidanhgia;
    }

    public void setIdnguoidanhgia(String idnguoidanhgia) {
        this.idnguoidanhgia = idnguoidanhgia;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }
}
