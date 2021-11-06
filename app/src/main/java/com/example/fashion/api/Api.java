package com.example.fashion.api;





import com.example.fashion.model.Images;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;
import com.example.fashion.model.ReponseModel;
import com.example.fashion.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("api/login.php")
    Call<ReponseModel> login(
//            @Field("email") String email,
//            @Field("hash_password") String hash_password
        @Body User user
    );

    @POST("api/register.php")
    Call<ReponseModel> register(
//            @Field("email") String email,
//            @Field("hash_password") String hash_password
            @Body User user
    );

    @POST("api/profile.php")
    Call<User> profile();


    @POST("api/resetpassword.php")
    Call<ReponseModel> resetPassword(@Body User user);

    @POST("api/getallproductid1.php")
    Call<ListResponse> getProductID1() ;

    @POST("api/getallcategory.php")
    Call<ListResponse> getImages(@Body Images images);
}
