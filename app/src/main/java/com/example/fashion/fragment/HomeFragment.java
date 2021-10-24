package com.example.fashion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.R;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rcv;
    SanPhamAdapter adapter;
    TextView xemTatCa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_home2, container, false);
        rcv = view.findViewById(R.id.rcvSanPham);
//        xemTatCa = view.findViewById(R.id.xemTatCa);
//        xemTatCa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),New));
//            }
//        });
        List<Products> list = new ArrayList<>();
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
        //recycview san pham

        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        adapter = new SanPhamAdapter(getActivity(), list);
        rcv.setAdapter(adapter);
         return view;
    }
}