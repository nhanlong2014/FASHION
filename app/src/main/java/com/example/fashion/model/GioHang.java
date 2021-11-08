package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

public class GioHang {
    @SerializedName("product_name")
    private String product_name;

    @SerializedName("description")
    private String description;

    @SerializedName("img_url_product")
    private String img_url_product;

    @SerializedName("price")
    private Double price;

    @SerializedName("id_cart")
    private int id_cart;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("id_user")
    private int id_user;

    @SerializedName("size")
    private String size;

    public GioHang(int id_user) {
        this.id_user = id_user;
    }

    public GioHang(String product_name, String description, int quantity, Double price, String img_url_product, int id_user, String size) {
        this.product_name = product_name;
        this.description = description;
        this.img_url_product = img_url_product;
        this.price = price;
        this.quantity = quantity;
        this.id_user = id_user;
        this.size = size;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public GioHang(String product_name, String description, String img_url_product, Double price, int id_cart, int quantity, int id_user, String size) {
        this.product_name = product_name;
        this.description = description;
        this.img_url_product = img_url_product;
        this.price = price;
        this.id_cart = id_cart;
        this.quantity = quantity;
        this.id_user = id_user;
        this.size = size;
    }
}
