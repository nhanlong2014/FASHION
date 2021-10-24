package com.example.fashion.api;





import com.example.fashion.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitService {
    @POST("user_login.php")
    Call<AccessToken> login(@Body User user);
//    @GET("getProfile.php")
//    Call<Users> getProfile();
//    @POST("user_login.php")
//    Call<AccessToken> login1(@Body Users users);
    @POST("user_register.php")
    Call<AccessToken> register(@Body User user);
//
//    //forgot password
//    @POST("user_forgot_password.php")
//    Call<AccessToken> forgot(@Body Users users);
}
