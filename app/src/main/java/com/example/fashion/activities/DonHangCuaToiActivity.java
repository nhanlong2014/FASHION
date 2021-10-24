package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.fashion.R;

public class DonHangCuaToiActivity extends AppCompatActivity {
ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_cua_toi);
        imgBack = findViewById(R.id.imgBack);
    }
}