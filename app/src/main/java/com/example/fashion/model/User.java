package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id_user")
    private String id_user;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("sdt")
    int sdt;

    public User() {
    }

    public User(String email, String password,String fullname, int sdt) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.sdt = sdt;
    }



    public User( String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
}
