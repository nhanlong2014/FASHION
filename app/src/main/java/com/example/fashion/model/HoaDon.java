package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

public class HoaDon {
    @SerializedName("id_order")
    private int id_order;
    @SerializedName("address")
    private String address;
    @SerializedName("total_price")
    private int total_price;
    @SerializedName("id_user")
    private int id_user;
    @SerializedName("date")
    private String date;
    @SerializedName("phone")
    private int phone;
    @SerializedName("fullname")
    private String fullname;

    public HoaDon() {
    }

    public HoaDon(int id_order,String fullname, int phone, String date, String address, int id_user, int total_price  ) {
        this.id_order = id_order;

        this.address = address;
        this.total_price = total_price;
        this.id_user = id_user;
        this.date = date;
        this.phone = phone;
        this.fullname = fullname;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String id_address) {
        this.address = address;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
