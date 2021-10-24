package com.example.fashion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.fashion.R;
import com.example.fashion.adapter.SlideImageAdapter;
import com.example.fashion.model.GioHang;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatButton btnAddToCart;
    ImageView imgChiTiet;
    TextView tvTenSP,edtTitle, tvGiaTienSP, tvChiTietSP;
    CheckBox chbs,chbm,chbl,chbxl;
    AppCompatButton btnSize38;
    Boolean isClickedDummy = true; // in your onCreate()
    SliderView sliderView;
    int[] images = {R.drawable.swe,
            R.drawable.swe,
            R.drawable.swe,
            R.drawable.swe};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        setToolbar();

        edtTitle = findViewById(R.id.edtTitle);
        imgChiTiet = findViewById(R.id.imgHinhChiTiet);
        tvTenSP = findViewById(R.id.tvTenChiTiet);
        tvGiaTienSP = findViewById(R.id.tvGiaChiTiet);
        tvChiTietSP = findViewById(R.id.tvChiTiet);
        chbs = findViewById(R.id.ckbS);
        chbm = findViewById(R.id.ckbM);
        chbl = findViewById(R.id.ckbL);
        chbxl = findViewById(R.id.ckbXL);

        Intent i = getIntent();

        String tenSanPham = i.getStringExtra("tenSanPham");
        String moTa = i.getStringExtra("moTa");
        String giaTien = i.getStringExtra("giaTien");
        String hinhAnh = i.getStringExtra("maHinhAnh");

        imgChiTiet.setImageResource(Integer.parseInt(hinhAnh));
        tvTenSP.setText(tenSanPham);
        tvChiTietSP.setText(moTa);
        tvGiaTienSP.setText(String.valueOf(giaTien));
        edtTitle.setText(tenSanPham);

        int[] images = {R.drawable.swe,
                R.drawable.swe,
                R.drawable.swe,
                R.drawable.swe};
//        //slide image
//        sliderView = findViewById(R.id.image_slider);
//
//        SlideImageAdapter sliderAdapter = new SlideImageAdapter(images);
//
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();




        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }



    //toolbar
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //addtoCart
    private void addToCart(){
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("tenSanPham",tvChiTietSP.getText().toString());
        cartMap.put("giaTien",tvTenSP.getText().toString());
        cartMap.put("maHinhAnh",imgChiTiet.getImageAlpha());
        Toast.makeText(ProductDetailsActivity.this, "Add thành công", Toast.LENGTH_SHORT).show();

    }

}