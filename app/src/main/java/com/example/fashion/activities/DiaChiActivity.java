package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.DiaChi;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaChiActivity extends AppCompatActivity {
    Button btnAddDiaChi;
    EditText edtDiaChi;
    ImageView imgBack, imgAddDiaChi;
    AccessTokenManager tokenManager;
    Spinner spinnerDiaChi;
    static List<DiaChi> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dia_chi);
        tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        spinnerDiaChi = findViewById(R.id.spinnerTest);

        btnAddDiaChi = findViewById(R.id.btnAddDC);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiaChiActivity.this, ThanhToanActivity.class);
                startActivity(i);
                finish();
            }
        });

//        btnAddDiaChi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edtDiaChi = findViewById(R.id.edtNhapDiaChi);
//                String diaChi1 = edtDiaChi.getText().toString();
//                Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        if (response.isSuccessful()) {
//                            String idUser = String.valueOf(response.body().getId_user());
////                            DiaChi diaChi = new DiaChi(diaChi1,6);
////                            spinnerDiaChi.setSelected(getIndex(spinnerDiaChi,diaChi));
//
////                            Call<ReponseModel> call1 = RetrofitClient.getApiClient().create(Api.class).addAddresses(diaChi);
//                            call1.enqueue(new Callback<ReponseModel>() {
//                                @Override
//                                public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
//                                    if (response.isSuccessful()) {
//
//                                        if (response.body().getStatus().equals("ok")) {
//
//                                            Toast.makeText(getApplicationContext(), "Thêm địa chỉ giao hàng thành công!", Toast.LENGTH_SHORT).show();
//                                            finish();
//
//                                        } else {
//                                            Toast.makeText(getApplicationContext(), "Thêm địa chỉ giao hàng thất bại!", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                    else {
//                                        Toast.makeText(getApplicationContext(), "Thêm địa chỉ giao hàng thất bại!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ReponseModel> call, Throwable t) {
//
//                                    Log.i("log",">>>>>>>>>>>>"+t.getMessage());
//                                }
//                            });
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Không có id", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "Sai trong id" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
        Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User model = response.body();

                    //lay id de gan vao dia chi
                    int idUser = response.body().getId_user();
                    //gan vao edittext
                    Toast.makeText(getApplicationContext(), "id nè" + idUser, Toast.LENGTH_SHORT).show();
                    DiaChi diaChi = new DiaChi(idUser);
                    Call<ListResponse> call1 = RetrofitClient.getApiClient().create(Api.class).getAllAddressById(diaChi);
                    call1.enqueue(new Callback<ListResponse>() {
                        @Override
                        public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                            if (response.isSuccessful()) {
                                list = response.body().getDiaChiList();
                                List<String> fishName = new ArrayList<String>();
//                                for (int i = 0; i < list.size(); i++) {
//                                    fishName.add(list.get(i).getAddress_name());
//                                }
//
//                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DiaChiActivity.this,
//                                        android.R.layout.simple_spinner_item, fishName);
//                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                spinnerDiaChi.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();


                            } else {
                                Toast.makeText(getApplicationContext(), "Sai trong spinner" + response.errorBody(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ListResponse> call, Throwable t) {

                            Toast.makeText(getApplicationContext(), "Sai trong giỏ hàng" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Không có id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sai trong id" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private int getIndex(Spinner spinnerDiaChi, DiaChi diaChi) {
//        for (int i = 0; i<spinnerDiaChi.getCount() i++){
//
//        }
        return 0;
    }


}