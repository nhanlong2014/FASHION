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

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class NewArrivalsActivity extends AppCompatActivity {
    RecyclerView rcv;
    SanPhamAdapter adapter;
    Toolbar toolbar;
    EditText edt;
    List<Products> list = new ArrayList<>();
    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_arrivals);
        setToolbar();

        setRecycelview();

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
    //recycview san pham
    private void setRecycelview(){
        list = new ArrayList<>();
        rcv = findViewById(R.id.rcvSanPham);
        list.add(new Products("1Vaaans Old School","1aaaaa",
                2.3,1,1,R.drawable.drew,"S",1));
        list.add(new Products("Abbbns Old School","aaaaa",
                1.800000,1,2,R.drawable.drew,"M",2));
        list.add(new Products("Bcccns Old School","aaaaa",
                4.800000,1,3,R.drawable.drew,"S",3));
        list.add(new Products("Vddddns Old School","aaaaa",
                1.600000,1,4,R.drawable.drew,"M",4));
        list.add(new Products("Deeeens Old School","aaaaa",
                1.300000,1,5,R.drawable.drew,"S",5));
        rcv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        adapter = new SanPhamAdapter(getApplicationContext(), list);
        rcv.setAdapter(adapter);
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

//    private void setupSort(ArrayList<Products> products) {
//        Spinner spinCountry= (Spinner) findViewById(R.id.spinner);//fetch the spinner from layout file
//        ArrayAdapter<Products> adapter = new ArrayAdapter<Products>
//                (this,android.R.layout.simple_spinner_item, products);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinCountry.setAdapter(adapter);
//        Collections.sort(atlastContacts, new Comparator<AtlasContact>(){
//            public int compare(AtlasContact a1, AtlasContact a2) {
//                return a1.getName().compareToIgnoreCase(a2.getName());
//            }
//        });
////        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                if (position == 0) {
////                    sortByName();
////                } else {
////                    sortByPrice();
////                }
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////                // TODO Auto-generated method stub
////            }
////        });
//    }
//
//    private void sortByName() {
//        Log.d(TAG, "sortByName: ");
//        Collections.sort(list,  (l1, l2) -> l1.getTenSanPham().compareTo(l2.getTenSanPham()));
//    }
//
//    private void sortByPrice() {
//        Log.d(TAG, "sortByPrice: ");
//        Collections.sort(list, (l1, l2) -> {
//            if (l1.getGiaTien() > l2.getGiaTien()) {
//                return 1;
//            } else if (l1.getGiaTien() < l2.getGiaTien()) {
//                return -1;
//            } else {
//                return 0;
//            }
//        });
//    }
}