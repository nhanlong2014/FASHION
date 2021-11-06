package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.AccessToken;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {
    private static final String TAG = "";
    EditText edtFullname, edtEmail, edtPassword, edtSDT;
    Button btnSignUp;
    private AccessTokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);


        edtFullname = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtSDT = findViewById(R.id.edtSDT);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = edtFullname.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String sdt = edtSDT.getText().toString();
                if (edtFullname.getText().toString().equals("")) {
                    Toast.makeText(DangKyActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
                } else if (edtEmail.getText().toString().equals("")) {
                    Toast.makeText(DangKyActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().equals("")) {
                    Toast.makeText(DangKyActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (edtSDT.getText().toString().equals("")) {
                    Toast.makeText(DangKyActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User(email,password,fullname,Integer.parseInt(sdt));
                    registerUser(user);
                }
            }

        });
    }

//    public void userRegister() {
////
////        IRetrofitService service = RetrofitBuilder.createService(IRetrofitService.class);
//////        tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
////
//
//
//        if (TextUtils.isEmpty(fullname)) {
//            edtFullname.setError("Không được để trống họ tên");
//            return;
//        } else if (TextUtils.isEmpty(email)) {
//            edtEmail.setError("Không được để trống email");
//            return;
//        } else if (TextUtils.isEmpty(password)) {
//            edtPassword.setError("Không được để trống password");
//            return;
//        } else if (TextUtils.isEmpty(sdtString)) {
//            edtSDT.setError("Không được để trống số điện thoại");
//            return;
//        }else {
//
//        }
//
//
//
//    }
////
    public void registerUser(User user){
        final ProgressDialog progressDialog = new ProgressDialog(DangKyActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ReponseModel> call = RetrofitClient.getApiClient().create(Api.class).register(user);
        Log.d(">>>>>>>>>>>>>>>>>>","user"+user);
        call.enqueue(new Callback<ReponseModel>() {
            @Override
            public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                if(response.code()==200)
                {
                    if(response.body().getStatus().equals("ok"))
                    {
                        if(response.body().getResultCode()==1)
                        {
                            Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                            Toast.makeText(DangKyActivity.this, "Tài khoản đã được sử dụng",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                        {
                            Toast.makeText(DangKyActivity.this, "Lỗi thông tin",
                                    Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    {
                        Toast.makeText(DangKyActivity.this, "Lỗi thông tin",
                                Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ReponseModel> call, Throwable t) {
                Toast.makeText(DangKyActivity.this, "Đừng zô đây",
                        Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }
}