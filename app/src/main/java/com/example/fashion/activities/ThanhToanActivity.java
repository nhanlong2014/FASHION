package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fashion.R;

public class ThanhToanActivity extends AppCompatActivity {
    AppCompatButton btn;
    TextView tvThemDiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        tvThemDiaChi = findViewById(R.id.tvThemDiaChi);
//        btn = findViewById(R.id.btnTha  btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),OrderThanhCongActivity.class));
//            }
//        });nhToanNgay);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),OrderThanhCongActivity.class));
//            }
//        });
        tvThemDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DiaChiActivity.class));
            }
        });
    }
}