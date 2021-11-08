package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse {
    @SerializedName("products")
    List<Products> productsList;

    @SerializedName("images")
    List<Images> imagesList;

    @SerializedName("carts")
    List<GioHang> cartList;

    String error;

    public ListResponse(List<Products> productsList, List<Images> imagesList, List<GioHang> cartList, String error) {
        this.productsList = productsList;
        this.imagesList = imagesList;
        this.cartList = cartList;
        this.error = error;
    }

    public List<GioHang> getCartList() {
        return cartList;
    }

    public void setCartList(List<GioHang> cartList) {
        this.cartList = cartList;
    }

    public ListResponse(List<Products> productsList, String error, List<Images> imagesList) {
        this.productsList = productsList;
        this.error = error;
        this.imagesList = imagesList;

    }



    public List<Images> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
