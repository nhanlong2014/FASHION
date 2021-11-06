package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.test);
        Log.d(">>>>>>>>.",">>>>>>>>>>>>>>");

        Call<User> call = RetrofitClient.getApiClient().create(Api.class).profile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User model = response.body();
                    textView.setText(model.getId_user());

                }
                else{
                    Toast.makeText(Test.this, "??????????"+ response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Test.this, ">>>>>>>>>"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("101112",">>>>>>>>>>>>>>");

    }
}