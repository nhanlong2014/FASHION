package com.example.fashion.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fashion.R;
import com.example.fashion.adapter.GioHangAdapter;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.adapter.SlideImageAdapter;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.Images;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.Size;
import com.example.fashion.model.User;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatButton btnAddToCart, btnCong, btnTru;
    Spinner spinner;
    TextView tvTenSP, tvTitle, tvGiaTienSP, tvChiTietSP, tvSoLuong;
    LinearLayout indicatorlay;
    private List<Images> list;
    ViewPager2 viewPager;
    SlideImageAdapter adapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 3;
    String cardStatusString;
    Products products;
    int totalQuantity = 0;
    int totalPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initView();
        viewPager();
        setBtnAddToCart();

        Products products = (Products) getIntent().getSerializableExtra("chitiet");
        int giaTien = products.getPrice();
        Log.i("log", ">>>>>>>>>" + giaTien);
        int product_id = products.getId_product();
        tvTenSP.setText(products.getProduct_name());
        tvChiTietSP.setText(products.getDescription());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaTienSP.setText("Giá: " + decimalFormat.format(giaTien) + "");
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    tvSoLuong.setText(String.valueOf(totalQuantity));
                    totalPrice = giaTien * totalQuantity;
                }
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    tvSoLuong.setText(String.valueOf(totalQuantity));
                    totalPrice = giaTien * totalQuantity;

                }
            }
        });


//lấy danh sách hình ảnh
        Images images = new Images(product_id);
        Call<ListResponse> call = RetrofitClient.getApiClient().create(Api.class).getImages(images);
        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()) {
                    list = response.body().getImagesList();
                    adapter = new SlideImageAdapter(list, getApplicationContext());
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
                Log.d(">>>>>>>>>>", "Log" + t.getMessage());

            }


        });

//add to cart
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                cardStatusString = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String product_name = products.getProduct_name();
                String description = getIntent().getStringExtra("description");
                String img_url_product = products.getImage_url_product();
                Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            int idUser = response.body().getId_user();
                            Toast.makeText(getApplicationContext(), "id nè" + idUser, Toast.LENGTH_SHORT).show();
                            GioHang gioHang = new GioHang(product_name, description,
                                    img_url_product, giaTien, totalQuantity,
                                    idUser, cardStatusString, totalPrice);
                            if (totalQuantity <= 0) {
                                Toast.makeText(getApplicationContext(), "Please add some items in cart.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Call<ReponseModel> call1 = RetrofitClient.getApiClient().create(Api.class).addToCart(gioHang);
                            call1.enqueue(new Callback<ReponseModel>() {
                                @Override
                                public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                                    if (response.isSuccessful()) {

                                        if (response.body().getStatus().equals("ok")) {

                                            Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thất bại!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ReponseModel> call, Throwable t) {
                                    System.out.println("onFailure");
                                    System.out.println(t.fillInStackTrace());
                                    Toast.makeText(getApplicationContext(), "Sai trong giỏ hàng" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(getApplicationContext(), "Không có id", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Sai trong id" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    private void setupIndicator() {
        ImageView[] indicator = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(4, 0, 4, 0);
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            indicatorlay.addView(indicator[i]);
        }

    }

    private void setupCurrentIndicator(int index) {
        int itemcildcount = indicatorlay.getChildCount();
        for (int i = 0; i < itemcildcount; i++) {
            ImageView imageView = (ImageView) indicatorlay.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
            }
        }
    }


    //slide image hình
    public void viewPager() {
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
    }


    //back toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initView() {
        spinner = findViewById(R.id.spinner);
        toolbar = findViewById(R.id.toolbarDetails);
        indicatorlay = findViewById(R.id.lay_indicator);
        viewPager = findViewById(R.id.viewPager);
        tvTitle = findViewById(R.id.edtTitle);
        tvTenSP = findViewById(R.id.tvTenChiTiet);
        tvGiaTienSP = findViewById(R.id.tvGiaChiTiet);
        tvChiTietSP = findViewById(R.id.tvChiTiet);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnCong = findViewById(R.id.btncong);
        btnTru = findViewById(R.id.btntru);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }



    public void setBtnAddToCart() {

    }


}