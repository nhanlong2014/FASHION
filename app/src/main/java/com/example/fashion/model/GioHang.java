package com.example.fashion.model;

public class GioHang {
    private String tenSanPham,kichThuoc;
    private Double giaTien,tongGiaTien;
    private int hinhAnh,soLuong;

    public GioHang(String tenSanPham, String kichThuoc, Double giaTien, Double tongGiaTien, int hinhAnh, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.kichThuoc = kichThuoc;
        this.giaTien = giaTien;
        this.tongGiaTien = tongGiaTien;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public Double getTongGiaTien() {
        return tongGiaTien;
    }

    public void setTongGiaTien(Double tongGiaTien) {
        this.tongGiaTien = tongGiaTien;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
