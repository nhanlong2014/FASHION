package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReponseModel {

    @SerializedName("status")
   private String status;
    @SerializedName("result_code")
   private int resultCode;
    @SerializedName("name")
   private String name;

    @SerializedName("access_token")
    private String accesss_token;

    public ReponseModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccesss_token() {
        return accesss_token;
    }

    public void setAccesss_token(String accesss_token) {
        this.accesss_token = accesss_token;
    }
    public ReponseModel(String access_token) {
    }

//    public List<Products> getList() {
//        return list;
//    }
//
//    public void setList(List<Products> list) {
//        this.list = list;
//    }
//
//    private List<Products> list;
//    public ReponseModel(String accesss_token) {
//        this.accesss_token = accesss_token;
//    }
//
//    public String getAccesss_token() {
//        return accesss_token;
//    }
//
//    public void setAccesss_token(String accesss_token) {
//        this.accesss_token = accesss_token;
//    }
//
//
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public int getResultCode() {
//        return resultCode;
//    }
//
//    public void setResultCode(int resultCode) {
//        this.resultCode = resultCode;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}