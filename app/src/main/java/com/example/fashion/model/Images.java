package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

public class Images {
//    @SerializedName("id_image")
    private int id_image;

//    @SerializedName("image_url")
    private String image_url;

//    @SerializedName("product_id")
    private String product_id ;

    public int getId_image() {
        return id_image;
    }

    public Images(String product_id) {
        this.product_id = product_id;
    }

    public Images(int id_image, String image_url, String product_id) {
        this.id_image = id_image;
        this.image_url = image_url;
        this.product_id = product_id;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
