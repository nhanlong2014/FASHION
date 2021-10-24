package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fashion.R;

public class DiaChiCuaToiActivity extends AppCompatActivity {
ImageView imgBack,imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_cua_toi);
        imgBack = findViewById(R.id.imgBack);
        imgAdd = findViewById(R.id.addDiaChi);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiaChiCuaToiActivity.this,AddDiaChiActivity.class);
                startActivity(i);
            }
        });
    }
}