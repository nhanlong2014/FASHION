package com.example.fashion.model;

import java.util.Date;

public class ThongBao {
    int maThongBao;
    String title, details,date;

    public ThongBao(int maThongBao, String title, String details, String date) {
        this.maThongBao = maThongBao;
        this.title = title;
        this.details = details;
        this.date = date;
    }

    public int getMaThongBao() {
        return maThongBao;
    }

    public void setMaThongBao(int maThongBao) {
        this.maThongBao = maThongBao;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
