package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.AccessToken;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.IRetrofitService;
import com.example.fashion.api.RetrofitBuilder;
import com.example.fashion.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {
    EditText edtFullname, edtEmail, edtPassword, edtSDT;
    Button btnSignUp;
    private AccessTokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

//        service = RetrofitBuilder.createService(IRetrofitService.class);


//        ApiService service = new ApiClient(ApiService.class);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }

        });
    }

    public void userRegister(){

        IRetrofitService service = RetrofitBuilder.createService(IRetrofitService.class);
        tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        edtFullname = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtSDT = findViewById(R.id.edtSDT);
        String fullname = edtFullname.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String sdtString = edtSDT.getText().toString();
        if (TextUtils.isEmpty(fullname)) {
            edtFullname.setError("Không được để trống họ tên");
            return;
        } else if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Không được để trống email");
            return;
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Không được để trống password");
            return;
        } else if (TextUtils.isEmpty(sdtString)) {
            edtSDT.setError("Không được để trống số điện thoại");
            return;
        } else {
            User user = new User(fullname, email, password, Integer.parseInt(sdtString));
                    service.register(user).enqueue(getRegister);
        }
    }

    //retrofit login
    Callback<AccessToken> getRegister = new Callback<AccessToken>() {
        @Override
        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
            if(response.isSuccessful()){
                Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DangKyActivity.this,DangNhapActivity.class));
            }else {
                Toast.makeText(DangKyActivity.this, "Tài khoản đã được sử dụng!!!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<AccessToken> call, Throwable t) {
            Toast.makeText(DangKyActivity.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();

        }
    };
}