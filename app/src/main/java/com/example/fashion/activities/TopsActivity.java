package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TopsActivity extends AppCompatActivity {
    RecyclerView rcv;
    SanPhamAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);
        setToolbar();

        rcv = findViewById(R.id.rcvSanPham);
        List<Products> products = new ArrayList<>();
        products.add(new Products("Vans Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        products.add(new Products("Vans Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        products.add(new Products("Vans Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        products.add(new Products("Vans Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        products.add(new Products("Vans Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        //recycview san pham
        rcv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        adapter = new SanPhamAdapter(getApplicationContext(), products);
        rcv.setAdapter(adapter);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Objects.requireNonNull(getSupportActionBar()).setTitle(0);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    }