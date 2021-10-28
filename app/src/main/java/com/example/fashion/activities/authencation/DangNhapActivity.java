package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.api.SharedPrefManager;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    TextView txt_signUp, txt_ForgotPassword;
    Button btnDangNhap;
    SharedPrefManager sharedPrefManager;
    private AccessTokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        btnDangNhap = findViewById(R.id.btnSignIn);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });


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
    }


    private void userLogin() {
        EditText edtEmail, edtPassword;
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtLoginPassword);
        String userEmail = edtEmail.getText().toString();
        String userPassword = edtPassword.getText().toString();
        User user = new User(userEmail, userPassword);
        if (userEmail.isEmpty()) {
            edtEmail.requestFocus();
            edtEmail.setError("Please enter your name");
            return;
        }

        if (userPassword.isEmpty()) {
            edtPassword.requestFocus();
            edtPassword.setError("Please enter your password");
            return;
        }
        if (userPassword.length() < 2) {
            edtPassword.requestFocus();
            edtPassword.setError("Please enter your name");
            return;
        }

        Call<ReponseModel> call = RetrofitClient.getInstance().getApi().login(user);

        call.enqueue(new Callback<ReponseModel>() {
            @Override
            public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {

                ReponseModel reponseModel = response.body();

                if (response.isSuccessful()) {

//                        sharedPrefManager.saveUser(loginResponse.getUser());
//                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    Toast.makeText(DangNhapActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<ReponseModel> call, Throwable t) {

                Toast.makeText(DangNhapActivity.this, "Kết nối thất bại!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}