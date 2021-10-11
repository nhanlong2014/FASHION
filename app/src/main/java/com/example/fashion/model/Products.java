package com.example.fashion.model;

public class Products {
    private String tenSanPham,moTa;
    private Double giaTien;
    private int soLuong;
    private int maHinhAnh, maKichThuoc, maTheLoai;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public int getMaHinhAnh() {
        return maHinhAnh;
    }

    public void setMaHinhAnh(int maHinhAnh) {
        this.maHinhAnh = maHinhAnh;
    }

    public int getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(int maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public Products(String tenSanPham, String moTa, Double giaTien, int soLuong, int maHinhAnh, int maKichThuoc, int maTheLoai) {
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.maHinhAnh = maHinhAnh;
        this.maKichThuoc = maKichThuoc;
        this.maTheLoai = maTheLoai;
    }
}
