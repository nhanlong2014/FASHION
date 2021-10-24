package com.example.fashion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HoodieActivity extends AppCompatActivity {
    RecyclerView rcv;
    SanPhamAdapter adapter;
    Toolbar toolbar;
    EditText edt;
    List<Products> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoodie);
        setToolbar();

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
        rcv = findViewById(R.id.rcvHoodies);
        list.add(new Products("Vaaans Old School","aaaaa",
                2.3,1,1,R.drawable.drew,"S",1));
        list.add(new Products("Abbbns Old School","aaaaa",
                1.800000,1,1,R.drawable.drew,"M",2));
        list.add(new Products("Bcccns Old School","aaaaa",
                4.800000,1,1,R.drawable.drew,"S",3));
        list.add(new Products("Vddddns Old School","aaaaa",
                1.600000,1,1,R.drawable.drew,"M",4));
        list.add(new Products("Deeeens Old School","aaaaa",
                1.300000,1,1,R.drawable.drew,"S",5));
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

//               Collections.sort(list, new Comparator<Products>() {
//                   @Override
//                   public int compare(Products c1, Products c2) {
//                       return Double.compare(c1.getGiaTien(), c2.getGiaTien());
//                   }
//               });
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}