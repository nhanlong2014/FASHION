package com.example.fashion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.R;
import com.example.fashion.activities.NewArrivalsActivity;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rcv;
    SanPhamAdapter adapter;
    TextView xemTatCa;
    ImageView imgSort;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_home2, container, false);
        rcv = view.findViewById(R.id.rcvSanPham);

        imgSort = view.findViewById(R.id.imgSort);
        imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        xemTatCa = view.findViewById(R.id.xemTatCa);
        xemTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewArrivalsActivity.class));
            }
        });
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
        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        adapter = new SanPhamAdapter(getActivity(), products);
        rcv.setAdapter(adapter);
         return view;
    }
}