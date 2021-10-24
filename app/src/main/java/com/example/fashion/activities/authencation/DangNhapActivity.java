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
import com.example.fashion.api.AccessToken;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.IRetrofitService;
import com.example.fashion.api.RetrofitBuilder;
import com.example.fashion.api.SharedPrefManager;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    TextView txt_signUp,txt_ForgotPassword;
    Button btnDangNhap;
    EditText edtEmail,edtPassword;
    SharedPrefManager sharedPrefManager;
    private AccessTokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
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
                startActivity(new Intent(DangNhapActivity.this,DangKyActivity.class));
            }
        });

        txt_ForgotPassword = findViewById(R.id.txt_ForgotPassword);
        txt_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this,QuenMatKhauActivity.class));
            }
        });
    }


    private void userLogin() {

        IRetrofitService service = RetrofitBuilder.createService(IRetrofitService.class);
        tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtLoginPassword);
        String userEmail = edtEmail.getText().toString();
        String userPassword = edtPassword.getText().toString();

        if(userEmail.isEmpty()){
            edtEmail.requestFocus();
            edtEmail.setError("Please enter your name");
            return;
        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
//            edtEmail.requestFocus();
//            edtEmail.setError("Please enter correct email");
//            return;
//        }
        else if(userPassword.isEmpty()){
            edtPassword.requestFocus();
            edtPassword.setError("Please enter your password");
            return;
        }
        else if(userPassword.length()<4){
            edtPassword.requestFocus();
            edtPassword.setError("Please enter your name");
            return;
        }else {
            service.login(new User(userEmail,userPassword)).enqueue(getLogin);

        }

    }

    Callback<AccessToken> getLogin = new Callback<AccessToken>() {
        @Override
        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
            if(response.isSuccessful()){
                AccessToken token = response.body();
                tokenManager.saveToken(token);
//                startActivity(new Intent(D.this,BottomActivity.class));
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
            }else {
//                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại!!!", Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onFailure(Call<AccessToken> call, Throwable t) {
            Toast.makeText(DangNhapActivity.this, "Kết nối thất bại!!!", Toast.LENGTH_SHORT).show();

        }
    };


}