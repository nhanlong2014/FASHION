package com.example.fashion.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DiaChi implements Serializable {

    @SerializedName("id_address")
    private int id_address;

    @SerializedName("tenduong")
    private String tenduong;

    @SerializedName("phuong")
    private String phuong;

    @SerializedName("tinh")
    private String tinh;

    @SerializedName("id_user")
    private int id_user;

    public DiaChi(int id_user) {
        this.id_user = id_user;
    }

    public DiaChi(String tenduong, String phuong, String tinh, int id_user) {
        this.tenduong = tenduong;
        this.phuong = phuong;
        this.tinh = tinh;
        this.id_user = id_user;
    }

    public int getId_address() {
        return id_address;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }

    public String getTenduong() {
        return tenduong;
    }

    public void setTenduong(String tenduong) {
        this.tenduong = tenduong;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
