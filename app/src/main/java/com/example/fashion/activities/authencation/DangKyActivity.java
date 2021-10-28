package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ReponseModel;
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

    public void userRegister() {
//
////        IRetrofitService service = RetrofitBuilder.createService(IRetrofitService.class);
////        tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
//
        edtFullname = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtSDT = findViewById(R.id.edtSDT);
        String fullname = edtFullname.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String sdtString = edtSDT.getText().toString();
        User user = new User(fullname, email, password, Integer.parseInt(sdtString), +1);
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
        }
        Call<ReponseModel> call = RetrofitClient.getInstance().getApi().register(user);

        call.enqueue(new Callback<ReponseModel>() {

            @Override
            public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                Log.d("Oncreatetestlog", "onCreate() Restoring previous state");
                System.out.println("Oncreatetestlog onCreate() Restoring previous state");
                if (response.isSuccessful()) {
                    ReponseModel model = response.body();
                    if (model.getStatus()) {
                        Toast.makeText(DangKyActivity.this, "Đăng ký thành công!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DangKyActivity.this, "Đăng ký thất bại!!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("insert2 onResponse>>>>", response.message());
                }


//                        sharedPrefManager.saveUser(loginResponse.getUser());
//                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);


            }

            @Override
            public void onFailure(Call<ReponseModel> call, Throwable t) {
                Log.d("LOG", t.getMessage());
            }
        });

    }
//
////    //retrofit login
////    Callback<AccessToken> getRegister = new Callback<AccessToken>() {
////        @Override
////        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
////            if(response.isSuccessful()){
////                Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công!!!", Toast.LENGTH_SHORT).show();
////                startActivity(new Intent(DangKyActivity.this,DangNhapActivity.class));
////            }else {
////                Toast.makeText(DangKyActivity.this, "Tài khoản đã được sử dụng!!!", Toast.LENGTH_SHORT).show();
////            }
////        }
////
////        @Override
////        public void onFailure(Call<AccessToken> call, Throwable t) {
////            Toast.makeText(DangKyActivity.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
////
////        }
////    };

}