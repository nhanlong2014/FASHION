package com.example.fashion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.R;
import com.example.fashion.activities.DiaChiActivity;
import com.example.fashion.adapter.GioHangAdapter;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.List;

public class GioHangFragment extends Fragment {

    AppCompatButton btn;
    RecyclerView rcv;
    GioHangAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        rcv = view.findViewById(R.id.rcv_giohang);

        intentThanhToan(view);

        List<GioHang> gioHangs = new ArrayList<>();
        gioHangs.add(new GioHang("Vans Old School", "S",
                1.800000, 1.800000, R.drawable.drew, 1));
        gioHangs.add(new GioHang("Vans Old School", "S",
                1.800000, 1.800000, R.drawable.drew, 1));
        gioHangs.add(new GioHang("Vans Old School", "S",
                1.800000, 1.800000, R.drawable.drew, 1));
        gioHangs.add(new GioHang("Vans Old School", "S",
                1.800000, 1.800000, R.drawable.drew, 1));
        gioHangs.add(new GioHang("Vans Old School", "S",
                1.800000, 1.800000, R.drawable.drew, 1));
        //recycview san pham
        rcv.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL, false));

        adapter = new GioHangAdapter(getActivity(), gioHangs);
        rcv.setAdapter(adapter);
        return view;
    }

    private void intentThanhToan(View view) {
        Button btn = view.findViewById(R.id.btnThanhToan);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DiaChiActivity.class));
            }
        });
    }
}