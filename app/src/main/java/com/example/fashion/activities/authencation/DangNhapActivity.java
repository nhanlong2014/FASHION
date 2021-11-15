package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    TextView txt_signUp, txt_ForgotPassword;
    Button btnDangNhap;
    EditText edtEmail, edtPassword;
    private AccessTokenManager tokenManager;
    private static String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtLoginPassword);

        txt_signUp = findViewById(R.id.txt_signUp);
        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });

        txt_ForgotPassword = findViewById(R.id.txt_ForgotPassword);
        txt_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, QuenMatKhauActivity.class));
            }
        });

        btnDangNhap = findViewById(R.id.btnSignIn);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (edtEmail.getText().toString().equals("")) {
                    Toast.makeText(DangNhapActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().equals("")) {
                    Toast.makeText(DangNhapActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(email, password);
                    loginUser(user);
                }
            }
        });
    }


    public void loginUser(User user) {
        tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        final ProgressDialog progressDialog = new ProgressDialog(DangNhapActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ReponseModel> call = RetrofitClient.getApiClient().create(Api.class).login(user);
        call.enqueue(new Callback<ReponseModel>() {
            @Override
            public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResultCode() == 1) {
                            ReponseModel reponseModel = response.body();
                            tokenManager.saveToken(reponseModel);
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công" ,
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangNhapActivity.this,Test.class));
                        } else {
                            Toast.makeText(DangNhapActivity.this, "Mật khẩu sai",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(DangNhapActivity.this, "Email không tồn tại",
                                Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(DangNhapActivity.this, "!!",
                            Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ReponseModel> call, Throwable t) {
                Toast.makeText(DangNhapActivity.this, "Đừng zô đây",
                        Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }
}