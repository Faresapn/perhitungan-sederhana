package com.faresa.aplikasikecambah.koneksi;


import com.faresa.aplikasikecambah.pojo.UserResponse;
import com.faresa.aplikasikecambah.pojo.kecambah.KecambahResponse;
import com.faresa.aplikasikecambah.pojo.login.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Service {

    @FormUrlEncoded
    @POST("user/login")
    Call<TokenResponse> loginRequest(@Field("email") String name,
                                     @Field("password") String password
    );
    @GET("user/getUser")
    Call<UserResponse>getUser(@Header("Authorization") String authorization);
    @GET("kecambah")
    Call<KecambahResponse> getKecambah(@Header("Authorization") String authorization);
}
