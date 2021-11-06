package com.example.fashion.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.R;
import com.example.fashion.activities.authencation.DangNhapActivity;
import com.example.fashion.activities.authencation.Test;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    SanPhamAdapter adapter;
    private List<Products> list = new ArrayList<>();
    RecyclerView rcv;
    AccessTokenManager accessTokenManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        return view;
    }
//    public void getProductById(View view) {
//
//
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accessTokenManager = AccessTokenManager.getInstance(getActivity().getSharedPreferences("prefs",MODE_PRIVATE));
        rcv = view.findViewById(R.id.rcvSanPham);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        Call<ListResponse> call = RetrofitClient.getApiClient().create(Api.class).getProductID1();
        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()) {
                    list = response.body().getProductsList();
                    rcv.setAdapter(new SanPhamAdapter(getActivity(), list));
                } else {
                    Toast.makeText(getActivity(), "Hệ thống gặp lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("LOGGGGGGGGGGGGGGGGGG", "" + t.getMessage());
            }
        });

    }
}