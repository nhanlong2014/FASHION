package com.example.fashion.api;





import com.example.fashion.model.DiaChi;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.HoaDon;
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


    //get san pham new arrival
    @POST("api/getallproductid1.php")
    Call<ListResponse> getProductID1() ;
    @POST("api/getallproductid2.php")
    Call<ListResponse> getProductID2() ;
    @POST("api/getallproductid3.php")
    Call<ListResponse> getProductID3() ;
    @POST("api/getallproductid4.php")
    Call<ListResponse> getProductID4() ;

    @POST("api/getallcategory.php")
    Call<ListResponse> getImages(@Body Images images);

    @POST("api/getAllCart.php")
    Call<ListResponse> getAllCart(@Body GioHang gioHang) ;

    @POST("api/addtocart.php")
    Call<ReponseModel> addToCart(@Body GioHang gioHang) ;

    @POST("api/deletecart.php")
    Call<ReponseModel> deleteToCart(@Body GioHang gioHang) ;


    //dia chi
    @POST("api/getAllAddress.php")
    Call<ListResponse> getAllAddressById(@Body DiaChi DiaChi) ;

    @POST("api/addAddresses.php")
    Call<ReponseModel> addAddresses(@Body DiaChi diaChi) ;

    @POST("api/order.php")
    Call<HoaDon> order(@Body HoaDon hoaDon) ;
}
