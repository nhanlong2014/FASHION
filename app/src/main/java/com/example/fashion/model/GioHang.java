package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GioHang implements Serializable {



    @SerializedName("product_name")
    private String product_name;

    @SerializedName("description")
    private String description;

    @SerializedName("img_url_product")
    private String img_url_product;

    @SerializedName("price")
    private int price;

    @SerializedName("id_cart")
    private int id_cart;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("id_user")
    private int id_user;

    @SerializedName("size")
    private String size;


    @SerializedName("total_price")
    private int total_price;

    public GioHang() {
    }

    public GioHang(int id_user) {
        this.id_user = id_user;
    }


//
//    public GioHang(String product_name, String description, int quantity, int price, String img_url_product, int id_user, String size) {
//        this.product_name = product_name;
//        this.description = description;
//        this.img_url_product = img_url_product;
//        this.price = price;
//        this.quantity = quantity;
//        this.id_user = id_user;
//        this.size = size;
//    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url_product() {
        return img_url_product;
    }

    public void setImg_url_product(String img_url_product) {
        this.img_url_product = img_url_product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_cart() {
        return id_cart;
    }

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public GioHang(String product_name, String description, String img_url_product, int price, int quantity, int id_user, String size, int total_price) {
        this.product_name = product_name;
        this.description = description;
        this.img_url_product = img_url_product;
        this.price = price;
        this.quantity = quantity;
        this.id_user = id_user;
        this.size = size;
        this.total_price = total_price;
    }

    public GioHang(String product_name, String description, String img_url_product, int price, int id_cart, int quantity, int id_user, String size, int total_price) {
        this.product_name = product_name;
        this.description = description;
        this.img_url_product = img_url_product;
        this.price = price;
        this.id_cart = id_cart;
        this.quantity = quantity;
        this.id_user = id_user;
        this.size = size;
        this.total_price = total_price;
    }
}
