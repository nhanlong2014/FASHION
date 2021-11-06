package com.example.fashion.activities.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashion.R;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMatKhauActivity extends AppCompatActivity {
    EditText edtEmailForgot;
    Button btnSend;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        edtEmailForgot = findViewById(R.id.edtEmailForgot);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailForgot.getText().toString();
                if (edtEmailForgot.getText().toString().equals("")) {
                    Toast.makeText(QuenMatKhauActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }else{
                    User user = new User(email,"");
                    resetPasswordUser(user);
                }

            }
        });
        //Create the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(QuenMatKhauActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    public void resetPasswordUser(User user) {
//        AccessTokenManager tokenManager = AccessTokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE)); //Create the Dialog here


        Call<ReponseModel> call = RetrofitClient.getApiClient().create(Api.class).resetPassword(user);
        call.enqueue(new Callback<ReponseModel>() {
            @Override
            public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResultCode() == 1) {
//                            tokenManager.deleteToken();
                            dialog.show(); // Showing the dialog here

                        } else {
                            Toast.makeText(QuenMatKhauActivity.this, "Gữi email không được",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(QuenMatKhauActivity.this, "Email không tồn tại",
                                Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(QuenMatKhauActivity.this, "!!",
                            Toast.LENGTH_SHORT).show();
                }
//                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ReponseModel> call, Throwable t) {
                Toast.makeText(QuenMatKhauActivity.this, "Đừng zô đây",
                        Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();

            }
        });
    }
}