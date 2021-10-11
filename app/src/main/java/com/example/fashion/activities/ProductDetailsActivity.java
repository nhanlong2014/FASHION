package com.example.fashion.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.fashion.R;
import com.example.fashion.adapter.SlideImageAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView tvTenSP;
    Toolbar toolbar;

    AppCompatButton btnSize38;
    Boolean isClickedDummy = true; // in your onCreate()
    SliderView sliderView;
    int[] images = {R.drawable.drew,
            R.drawable.drew,
            R.drawable.drew,
            R.drawable.drew};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        btnSize38 = findViewById(R.id.btnsizexl);
        btnSize38.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    btnSize38.setBackgroundColor(Color.RED);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnSize38.setBackgroundColor(Color.BLUE);
                }
                return false;
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        tvTenSP = findViewById(R.id.tvTenSP);
//        Intent i = getIntent();
//        String tenSP = i.getStringExtra("tenSp");
//        tvTenSP.setText(tenSP);

        //slide image
        sliderView = findViewById(R.id.image_slider);

        SlideImageAdapter sliderAdapter = new SlideImageAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}