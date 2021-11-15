package com.example.fashion.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

public class Products implements Serializable {
    @SerializedName("product_name")
    private String product_name;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private int price;



    @SerializedName("id_product")
    private int id_product;

    @SerializedName("image_url_product")
    private String image_url_product;

    @SerializedName("category_id")
    private int category_id;


    public Products() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getImage_url_product() {
        return image_url_product;
    }

    public void setImage_url_product(String image_url_product) {
        this.image_url_product = image_url_product;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }



    public static Comparator<Products> productsAZComparator = new Comparator<Products>() {
        @Override
        public int compare(Products o1, Products o2) {
            return o1.getProduct_name().compareTo(o2.getProduct_name());
        }
    };
    public static Comparator<Products> productsPriceComparator = new Comparator<Products>() {
        @Override
        public int compare(Products c1, Products c2) {
            return Double.compare(c1.getPrice(),c2.getPrice());
        }
    };

//     Collections.sort(list, new Comparator<Products>() {
//        @Override
//        public int compare(Products c1, Products c2) {
//            return Double.compare(c1.getGiaTien(), c2.getGiaTien());
//        }
//    });
}
