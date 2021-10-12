package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TopsActivity extends AppCompatActivity {
    RecyclerView rcv;
    SanPhamAdapter adapter;
    Toolbar toolbar;
    EditText edt;
    List<Products> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);

        setToolbar();
        setRecycelview(list);

        edt = findViewById(R.id.edtSearch);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });

    }

    //search
    private void filter(String toString) {
        ArrayList<Products> filterList = new ArrayList<>();
        for(Products item: list){
            if(item.getTenSanPham().toLowerCase().contains(toString.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.getFilter(filterList);
    }

    //recycview san pham
    private void setRecycelview(List<Products> list){
        rcv = findViewById(R.id.rcvTops);
        list.add(new Products("Vaaans Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        list.add(new Products("Vbbbns Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        list.add(new Products("Vcccns Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        list.add(new Products("Vddddns Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        list.add(new Products("Veeeens Old School","aaaaa",
                1.800000,1,R.drawable.drew,1,1));
        rcv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        adapter = new SanPhamAdapter(getApplicationContext(), list);
        rcv.setAdapter(adapter);
    }

    //toolbar
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    }