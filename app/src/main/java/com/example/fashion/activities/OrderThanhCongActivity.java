package com.example.fashion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion.MainActivity;
import com.example.fashion.R;
import com.example.fashion.activities.authencation.DangKyActivity;
import com.example.fashion.adapter.GioHangAdapter;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.DiaChi;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.HoaDon;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderThanhCongActivity extends AppCompatActivity {
    Button btn;
    TextView totalPrice, tvaddDiaChi;
    RecyclerView rcv;
    GioHangAdapter adapter;
    GioHang gioHang;
    Spinner spinnerDiaChi;
    EditText edtTen, edtSdt;
    static List<DiaChi> list = new ArrayList<>();
    int totalAmount = 0;
    String cardStatusString;
    AccessTokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_thanh_cong);

        tvaddDiaChi = findViewById(R.id.addDiaChi);
        edtSdt = findViewById(R.id.edtSDTNguoiNhan);
        edtTen = findViewById(R.id.edtTenNguoiNhan);
        totalPrice = findViewById(R.id.tvTongCong);
        spinnerDiaChi = findViewById(R.id.spinnerDiaChi);
        btn = findViewById(R.id.themHoaDon);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        List<GioHang> thumbs = (List<GioHang>) bundle.getSerializable("list");
        calculateTotalAmount(thumbs);

        tvaddDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogAddDiaChi();
            }
        });




        Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User model = response.body();

                    //lay id de gan vao dia chi
                    int idUser = response.body().getId_user();
                    //gan vao edittext
                    edtTen.setText(String.valueOf(model.getFullname()));
                    edtSdt.setText(String.valueOf(model.getSdt()));
                    Toast.makeText(getApplicationContext(), "id nè" + idUser, Toast.LENGTH_SHORT).show();
                    DiaChi diaChi = new DiaChi(idUser);
                    Call<ListResponse> call1 = RetrofitClient.getApiClient().create(Api.class).getAllAddressById(diaChi);
                    call1.enqueue(new Callback<ListResponse>() {
                        @Override
                        public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                            if (response.isSuccessful()) {
                                list = response.body().getDiaChiList();
                                List<String> dc = new ArrayList<String>();

                                for (int i = 0; i < list.size(); i++) {
                                    dc.add(
                                            list.get(i).getTenduong()+ ", " +
                                                    list.get(i).getPhuong()+ ", " +
                                                    list.get(i).getTinh());
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OrderThanhCongActivity.this,
                                        android.R.layout.simple_spinner_item, dc);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerDiaChi.setAdapter(adapter);
                                adapter.notifyDataSetChanged();


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



        //lấy vị trí string của spinner
        spinnerDiaChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardStatusString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> call2 = RetrofitClient.getApiClient().create(Api.class).profile();
                tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
                call2.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User model = response.body();
                            int idUser = response.body().getId_user();
                            String ten = edtTen.getText().toString();
                            String sdt = edtSdt.getText().toString();

                            SimpleDateFormat timeStampFormat = new SimpleDateFormat(  "HH:mm:ss dd/MM/yyyy ");
                            Date myDate = new Date();
                            String filename = timeStampFormat.format(myDate);
                            HoaDon hoaDon = new HoaDon(-1,ten,Integer.parseInt(sdt),filename,cardStatusString,idUser,totalAmount);

                            Call<HoaDon> call3 = RetrofitClient.getApiClient().create(Api.class).order(hoaDon);
                            call3.enqueue(new Callback<HoaDon>() {
                                @Override
                                public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                                    if(response.isSuccessful()){
                                        int id = response.body().getId_order();
                                        Log.i("log","????????"+id);
                                       Toast.makeText(OrderThanhCongActivity.this, "Order thành công!",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        Toast.makeText(OrderThanhCongActivity.this, "Lỗi thông tin1",
                                                Toast.LENGTH_SHORT).show();
                                    }



                                }

                                @Override
                                public void onFailure(Call<HoaDon> call, Throwable t) {

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
        });


    }


    //thêm địa chỉ
    public void diaLogAddDiaChi(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.activity_add_dia_chi, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(OrderThanhCongActivity.this);
        alertDialogBuilderUserInput.setView(mView);

        final EditText edtTenDuong = (EditText) mView.findViewById(R.id.tenduong);
        final EditText edtPhuong = (EditText) mView.findViewById(R.id.phuong);
        final EditText edtTinh = (EditText) mView.findViewById(R.id.tinh);

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        String tenduong = edtTenDuong.getText().toString();
                        String phuong = edtPhuong.getText().toString();
                        String tinh = edtTinh.getText().toString();

                        Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.isSuccessful()) {
                                    int idUser = response.body().getId_user();
                                    Toast.makeText(getApplicationContext(), "id nè" + idUser, Toast.LENGTH_SHORT).show();
                                    DiaChi diaChi = new DiaChi(tenduong,phuong,tinh, idUser);

                                    Call<ReponseModel> call1 = RetrofitClient.getApiClient().create(Api.class).addAddresses(diaChi);
                                    call1.enqueue(new Callback<ReponseModel>() {
                                        @Override
                                        public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                                            if (response.isSuccessful()) {

                                                if (response.body().getStatus().equals("ok")) {

                                                    Toast.makeText(getApplicationContext(), "Thêm địa chỉ giao hàng thành công!", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Thêm địa chỉ giao hàng thất bại!", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Thêm địa chỉ giao hàng thất bại!", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ReponseModel> call, Throwable t) {

                                            Log.i("log", ">>>>>>>>>>>>" + t.getMessage());
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
                })

                .setNegativeButton("Hủy",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    private void calculateTotalAmount(List<GioHang> list) {

        for (GioHang gioHang : list) {
            totalAmount += gioHang.getTotal_price();
        }
        totalPrice.setText(String.valueOf(totalAmount));
    }
}