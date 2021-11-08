package com.example.fashion.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.MainActivity;
import com.example.fashion.R;
import com.example.fashion.activities.DiaChiActivity;
import com.example.fashion.activities.ThanhToanActivity;
import com.example.fashion.activities.authencation.Test;
import com.example.fashion.adapter.GioHangAdapter;
import com.example.fashion.adapter.SanPhamAdapter;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.Images;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangFragment extends Fragment {

    AppCompatButton btn;
    RecyclerView rcv;
    GioHangAdapter adapter;
    TextView tvTongTienTatCa;
    Double tongtien = 0.0;
    private List<GioHang> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        intentThanhToan(view);


        //recycview san pham

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTongTienTatCa = view.findViewById(R.id.tvTongTienTatCa);
        rcv = view.findViewById(R.id.rcv_giohang);
        rcv.setHasFixedSize(true);
//        SharedPreferences myPrefs = getActivity().getSharedPreferences("ACCESS_TOKEN",MODE_PRIVATE);
//        String id = myPrefs.getString("ACCESS_TOKEN",null);
//        tvTongTienTatCa.setText(id);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//        String product_id = getActivity().getIntent().getStringExtra("product_id");
//        Images images = new Images(product_id);
//        GioHang gioHang = new GioHang(6);
//        Call<ListResponse> call = RetrofitClient.getApiClient().create(Api.class).getAllCart(gioHang);
//        call.enqueue(new Callback<ListResponse>() {
//            @Override
//            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
//                if (response.isSuccessful()) {
//
//                } else {
//                    Toast.makeText(getActivity(), "Hệ thống gặp lỗi", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("LOGGGGGGGGGGGGGGGGGG", "" + t.getMessage());
//            }
//        });
        Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    int idUser = Integer.parseInt(response.body().getId_user());
//                    Toast.makeText(getActivity(), "id user nè" + idUser, Toast.LENGTH_SHORT).show();
                    GioHang gioHang = new GioHang(idUser);
                    Call<ListResponse> call1 = RetrofitClient.getApiClient().create(Api.class).getAllCart(gioHang);
                    call1.enqueue(new Callback<ListResponse>() {
                        @Override
                        public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                            if (response.isSuccessful()) {
                                list = response.body().getCartList();
                                rcv.setAdapter(new GioHangAdapter(getActivity(), list));
                            }
                        }

                        @Override
                        public void onFailure(Call<ListResponse> call, Throwable t) {

                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "??????????" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), ">>>>>>>>>" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void intentThanhToan(View view) {
        Button btn = view.findViewById(R.id.btnThanhToan);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThanhToanActivity.class));
            }
        });
    }

//    public void onCart(List<GioHang> list){
//        do
//    }


}