package com.example.fashion.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static String BASE_URL = "http://10.0.2.2:8081/views/";
    private static final Retrofit retrofit = buildRetrofit();

    private static Retrofit buildRetrofit(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String accesss_token = AccessTokenManager.getInstance(null).getToken().getAccess_token();
                Request request = chain.request().newBuilder()
                        //ghi đúng tên và dấu cách
                        .addHeader("Authorization","Bearer " +accesss_token).build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder().setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public static  <T> T createService(Class<T> service){
        return retrofit.create(service);
    }
}
