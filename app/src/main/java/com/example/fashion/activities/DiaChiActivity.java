    package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fashion.R;

    public class DiaChiActivity extends AppCompatActivity {
    AppCompatButton btn;
    ImageView imgBack,imgAddDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);
        imgBack = findViewById(R.id.imgBack);
        imgAddDiaChi = findViewById(R.id.addDiaChi);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiaChiActivity.this,ThanhToanActivity.class);
                startActivity(i);
                finish();
            }
        });
        imgAddDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiaChiActivity.this,AddDiaChiActivity.class);
                startActivity(i);
            }
        });
//        btn = findViewById(R.id.btnDiaChi);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DiaChiActivity.this,ThanhToanActivity.class));
//            }
//        });
    }
}