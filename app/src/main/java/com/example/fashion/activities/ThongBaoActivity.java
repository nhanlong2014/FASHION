package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.adapter.ThongBaoAdapter;
import com.example.fashion.model.Products;
import com.example.fashion.model.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcv;
    ThongBaoAdapter adapter;
        List<ThongBao> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        setToolbar();
        setRecycelview(list);
    }


    //recycview san pham
    private void setRecycelview(List<ThongBao> list){
        rcv = findViewById(R.id.rcvThongBao);
        list.add(new ThongBao(1,"Vaaans Old School",
                "aaaaaaaaaaaaa","31/12/2001"));
        list.add(new ThongBao(2,"Vaaans Old School",
                "aaaaaaaaaaaaa","31/12/2001"));
        list.add(new ThongBao(3,"Vaaans Old School",
                "aaaaaaaaaaaaa","31/12/2001"));
        list.add(new ThongBao(4,"Vaaans Old School",
                "aaaaaaaaaaaaa","31/12/2001"));
        list.add(new ThongBao(5,"Vaaans Old School",
                "aaaaaaaaaaaaa","31/12/2001"));
        rcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new ThongBaoAdapter(getApplicationContext(), list);
        rcv.setAdapter(adapter);
    }

    //toolbar
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}