package com.example.fashion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.adapter.SlideImageAdapter;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.Images;
import com.example.fashion.model.ListResponse;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatButton btnAddToCart;
    ImageView imgChiTiet;
    TextView tvTenSP,edtTitle, tvGiaTienSP, tvChiTietSP;
    CheckBox chbs,chbm,chbl,chbxl;
    AppCompatButton btnSize38;
    Boolean isClickedDummy = true; // in your onCreate()
    SliderView sliderView;
    LinearLayout indicatorlay;

    private List<Images> list;
    ViewPager2 viewPager;
    SlideImageAdapter adapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        setToolbar();
        indicatorlay = findViewById(R.id.lay_indicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setupCurrentIndicator(position);
            }
        });
        //NUM_PAGES =onBordingLists.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        edtTitle = findViewById(R.id.edtTitle);
        tvTenSP = findViewById(R.id.tvTenChiTiet);
        tvGiaTienSP = findViewById(R.id.tvGiaChiTiet);
        tvChiTietSP = findViewById(R.id.tvChiTiet);
        chbs = findViewById(R.id.ckbS);
        chbm = findViewById(R.id.ckbM);
        chbl = findViewById(R.id.ckbL);
        chbxl = findViewById(R.id.ckbXL);
        btnAddToCart = findViewById(R.id.btnAddToCart);


        String tenSanPham = getIntent().getStringExtra("product_name");
        String moTa = getIntent().getStringExtra("description");
        String giaTien = getIntent().getStringExtra("price");
        String product_id = getIntent().getStringExtra("product_id");
        tvTenSP.setText(tenSanPham);
        tvChiTietSP.setText(moTa);
        tvGiaTienSP.setText(String.valueOf(giaTien));
        edtTitle.setText(tenSanPham);
        Images images = new Images(product_id);

        Call<ListResponse> call = RetrofitClient.getApiClient().create(Api.class).getImages(images);
        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()) {
                    list = response.body().getImagesList();
                    adapter = new SlideImageAdapter(list,getApplicationContext());
                    viewPager.setAdapter(adapter);
                    setupIndicator();
                    setupCurrentIndicator(0);
                } else {
                    Toast.makeText(getApplicationContext(), "Hệ thống gặp lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(">>>>>>>>>>","Log"+t.getMessage());

            }


        });



        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }





    private void setupIndicator() {
        ImageView[] indicator=new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(4,0,4,0);
        for (int i=0; i<indicator.length; i++){
            indicator[i]=new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            indicatorlay.addView(indicator[i]);
        }

    }
    private void setupCurrentIndicator(int index) {
        int itemcildcount=indicatorlay.getChildCount();
        for (int i=0; i<itemcildcount; i++){
            ImageView imageView=(ImageView)indicatorlay.getChildAt(i);
            if (i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_active));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_inactive));
            }
        }
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