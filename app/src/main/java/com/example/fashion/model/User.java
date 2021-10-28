package com.example.fashion.model;

public class User {
    String fullname,email,password;
    int sdt,id_user;

    public User(String fullname, String email, String password, int sdt) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.sdt = sdt;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public User( String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String fullname, String email, String password, int sdt, int id_user) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.sdt = sdt;
        this.id_user = id_user;
    }

//    public User(String fullname, String email, String password, int sdt) {
//        this.fullname = fullname;
//        this.email = email;
//        this.password = password;
//        this.sdt = sdt;
//    }

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

    public void setPassword(String hash_password) {
        this.password = password;
    }



    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
}
