package com.example.fashion.model;

public class TheLoai {
    String tenTheLoai;
    int  imgTheLoai, maTheLoai;

    public TheLoai(int imgTheLoai, String tenTheLoai, int maTheLoai) {
        this.imgTheLoai = imgTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.maTheLoai = maTheLoai;
    }

    public int getImgTheLoai() {
        return imgTheLoai;
    }

    public void setImgTheLoai(int imgTheLoai) {
        this.imgTheLoai = imgTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
}
