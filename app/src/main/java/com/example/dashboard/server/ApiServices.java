package com.example.dashboard.server;

import com.example.dashboard.models.ListLocationModel;
import com.example.dashboard.server.response.response_petugas;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/register")
    Call<ResponseBody> Register(
            @Field("email") String email,
            @Field("nama") String nama,
             @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Field("password") String password
    );


    @GET("maps.php")
    Call<ListLocationModel> getAllLocation();

    @GET("user/getuser")
    Call<response_petugas> getuser();
}
