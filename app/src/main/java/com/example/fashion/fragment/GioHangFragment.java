package com.example.fashion.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.MainActivity;
import com.example.fashion.R;
import com.example.fashion.activities.DiaChiActivity;
import com.example.fashion.activities.OrderThanhCongActivity;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangFragment extends Fragment {
    List<GioHang> list;
    TextView tvTongTienTatCa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        intentThanhToan(view);

         tvTongTienTatCa = view.findViewById(R.id.tvTongTienTatCa);
        rcvList(view);
        setBtnThanhToan(view);
        return view;
    }


    private void setBtnThanhToan(View view) {
        Button btnThanhToan = view.findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity() , OrderThanhCongActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) list);
                intent.putExtras(bundle);
                startActivity(intent);

//                for (int i = 0; i < products.size(); i++) {
//                    String stockCategoryName = products.get(i).getStockCategoryName();
//                    Toast.makeText(HomeActivity.this, "" + stockCategoryName, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
//                    intent.putExtra("category", (Serializable) products);
//                }
//               startActivity(intent);
//                        i.put("getProduct_name", s);
//
//                        String s ="";
//                        for (int i = 0; i < list.size(); i++) {
//                            s= list.get(i).getProduct_name();
//                            Log.d("log1", ">>>>>>>>" + s);
//
//                        }
//                        Toast.makeText(getActivity(), "sao1" + , Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getActivity(), OrderThanhCongActivity.class);
//                        i.putExtra("getProduct_name", s);
//
//                        startActivity(i);
//                    }
//                });


//
//                startActivity(i);
            }
        });
    }

    private void rcvList(View view) {

        RecyclerView rcv = view.findViewById(R.id.rcv_giohang);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    int idUser = response.body().getId_user();
                    GioHang gioHang = new GioHang(idUser);
                    Call<ListResponse> call1 = RetrofitClient.getApiClient().create(Api.class).getAllCart(gioHang);
                    call1.enqueue(new Callback<ListResponse>() {
                        @Override
                        public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                            if (response.isSuccessful()) {
                                list = response.body().getCartList();
                                rcv.setAdapter(new GioHangAdapter(getActivity(), list));
                                calculateTotalAmount(list);

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


    private void calculateTotalAmount(List<GioHang> list) {

        int totalAmount = 0;
        for (GioHang gioHang : list) {
            totalAmount += gioHang.getTotal_price();
        }
        tvTongTienTatCa.setText(String.valueOf(totalAmount));
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

    @Override
    public void onResume() {
        super.onResume();

    }

    //    public void onCart(List<GioHang> list){
//        do
//    }
//    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent){
//            int totalAmount = 0;
//            fir
//            int totalBill = intent.getIntExtra("totalAmount",0);
//            tvTongTienTatCa.setText("."+ totalBill + ".");
//        }
//    };


}