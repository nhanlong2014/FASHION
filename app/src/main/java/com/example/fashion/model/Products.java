package com.example.fashion.model;

import java.util.Collections;
import java.util.Comparator;

public class Products {
    private String tenSanPham,moTa,kichThuoc;
    private Double giaTien;
    private int soLuong;
    private int maSanPham,maHinhAnh, maTheLoai;

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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaHinhAnh() {
        return maHinhAnh;
    }

    public void setMaHinhAnh(int maHinhAnh) {
        this.maHinhAnh = maHinhAnh;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.kichThuoc = maKichThuoc;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public Products(String tenSanPham, String moTa, Double giaTien, int soLuong, int maSanPham, int maHinhAnh, String kichThuoc, int maTheLoai) {
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.maSanPham = maSanPham;
        this.maHinhAnh = maHinhAnh;
        this.kichThuoc = kichThuoc;
        this.maTheLoai = maTheLoai;
    }

    public static Comparator<Products> productsAZComparator = new Comparator<Products>() {
        @Override
        public int compare(Products o1, Products o2) {
            return o1.getTenSanPham().compareTo(o2.getTenSanPham());
        }
    };
    public static Comparator<Products> productsPriceComparator = new Comparator<Products>() {
        @Override
        public int compare(Products c1, Products c2) {
            return Double.compare(c1.getGiaTien(),c2.getGiaTien());
        }
    };

//     Collections.sort(list, new Comparator<Products>() {
//        @Override
//        public int compare(Products c1, Products c2) {
//            return Double.compare(c1.getGiaTien(), c2.getGiaTien());
//        }
//    });
}
