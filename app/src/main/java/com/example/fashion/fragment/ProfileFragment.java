package com.example.fashion.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.fashion.R;
import com.example.fashion.activities.DiaChiCuaToiActivity;
import com.example.fashion.activities.DonHangCuaToiActivity;
import com.example.fashion.activities.SanPhamYeuThichActivity;
import com.example.fashion.activities.ThanhToanActivity;
import com.example.fashion.activities.authencation.DangKyActivity;
import com.example.fashion.activities.authencation.DangNhapActivity;
import com.example.fashion.activities.authencation.QuenMatKhauActivity;
import com.example.fashion.api.AccessToken;
import com.example.fashion.api.AccessTokenManager;
import com.example.fashion.api.SharedPrefManager;
import com.example.fashion.model.User;




public class ProfileFragment extends Fragment {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    TextView textView, txt_signUp;
    TextView txt_ForgotPassword;
    private AccessTokenManager tokenManager;
    LinearLayout rlt1, rlt2, rlt3, rlt4;
    AppCompatButton rlt5;
    LinearLayout linearLayout;
    Button btnDangNhap;
    EditText edtEmail,edtPassword;
    AppCompatButton btnDangXuat;
    SharedPrefManager sharedPrefManager;
    RelativeLayout rltDonHang,rltYeuThich,rltDiaChi;
    TextView tvDonHang,tvYeuThich,tvDiaChi;


//    private static String BASE_URL = "http://10.0.2.2:8081/views/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);
        textView = view.findViewById(R.id.username);
        tvDonHang= view.findViewById(R.id.tvDonHang);
        tvYeuThich = view.findViewById(R.id.tvYeuThich);
        tvDiaChi= view.findViewById(R.id.tvDiaChi);
        tvDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DonHangCuaToiActivity.class));
            }
        });
        tvYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SanPhamYeuThichActivity.class));
            }
        });
        tvDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DiaChiCuaToiActivity.class));
            }
        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(this, DangNhapActivity
//                        .class), REQUEST_CODE);
//
//            }
//        });
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);

//        sharedPrefManager=new SharedPrefManager(getActivity());


        txt_signUp = view.findViewById(R.id.txt_signUp);
        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DangKyActivity.class));
            }
        });
        txt_ForgotPassword = view.findViewById(R.id.txt_ForgotPassword);
        txt_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QuenMatKhauActivity.class));
            }
        });


        linearLayout = view.findViewById(R.id.linear6);

        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlt1.setVisibility(View.GONE);
//            rlt2.setVisibility(View.VISIBLE);
//            rlt3.setVisibility(View.VISIBLE);
//            rlt4.setVisibility(View.VISIBLE);

                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        btnDangNhap = view.findViewById(R.id.btnSignIn);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userLogin(view);
//
//                }


//
////            rlt2.setVisibility(View.VISIBLE);
////            rlt3.setVisibility(View.VISIBLE);
////            rlt4.setVisibility(View.VISIBLE);
//
     }
        });
//
        return view;
  }
//
//    private void userLogin(View view) {
//
//        IRetrofitService service = RetrofitBuilder.createService(IRetrofitService.class);
//
//        edtEmail = view.findViewById(R.id.edtLoginEmail);
//        edtPassword = view.findViewById(R.id.edtLoginPassword);
//        String userEmail = edtEmail.getText().toString();
//        String userPassword = edtPassword.getText().toString();
//
//        if(userEmail.isEmpty()){
//            edtEmail.requestFocus();
//            edtEmail.setError("Please enter your name");
//            return;
//        }
////        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
////            edtEmail.requestFocus();
////            edtEmail.setError("Please enter correct email");
////            return;
////        }
//        else if(userPassword.isEmpty()){
//            edtPassword.requestFocus();
//            edtPassword.setError("Please enter your password");
//            return;
//        }
//        else if(userPassword.length()<4){
//            edtPassword.requestFocus();
//            edtPassword.setError("Please enter your name");
//            return;
//        }else {
//            service.login(new User(userEmail,userPassword)).enqueue(getLogin);
//
//        }
//
//    }
//
//    Callback<AccessToken> getLogin = new Callback<AccessToken>() {
//        @Override
//        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
//            if(response.isSuccessful()){
//                AccessToken token = response.body();
//                tokenManager.saveToken(token);
////                startActivity(new Intent(D.this,BottomActivity.class));
//                rlt1.setVisibility(View.VISIBLE);
//                linearLayout.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
//            }else {
////                progressBar.setVisibility(View.VISIBLE);
//                Toast.makeText(getActivity(), "Đăng nhập thất bại!!!", Toast.LENGTH_SHORT).show();
////                progressBar.setVisibility(View.INVISIBLE);
//            }
//        }
//
//        @Override
//        public void onFailure(Call<AccessToken> call, Throwable t) {
//            Toast.makeText(getActivity(), "Kết nối thất bại!!!", Toast.LENGTH_SHORT).show();
//
//        }
//    };

}