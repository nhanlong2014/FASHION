package com.example.fashion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewArrivalsActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edt;

    SanPhamAdapter adapter;
    private List<Products> list = new ArrayList<>();
    RecyclerView rcv;
    AccessTokenManager accessTokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_arrivals);
        setToolbar();
        getProductById();

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
            if(item.getProduct_name().toLowerCase().contains(toString.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.getFilter(filterList);
    }

    //recycview san pham/
    public void getProductById() {
        accessTokenManager = AccessTokenManager.getInstance(getApplication().getSharedPreferences("prefs",MODE_PRIVATE));
        rcv = findViewById(R.id.rcvSanPham1);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new GridLayoutManager(getApplication(), 2));

        Call<ListResponse> call = RetrofitClient.getApiClient().create(Api.class).getProductID1();
        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()) {
                    list = response.body().getProductsList();
                    rcv.setAdapter(new SanPhamAdapter(getApplication(), list));
                } else {
                    Toast.makeText(getApplication(), "Hệ thống gặp lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("LOGGGGGGGGGGGGGGGGGG", "" + t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort,menu);
        return true;
    }



    //check theo số tiền hoặc chữ cái
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
       switch (id){
           case R.id.sort1:
               Collections.sort(list, Products.productsAZComparator);
               adapter.notifyDataSetChanged();
               return true;
           case R.id.sort2:
               Collections.sort(list, Products.productsPriceComparator);

               adapter.notifyDataSetChanged();
               return true;
       }
        return super.onOptionsItemSelected(item);
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