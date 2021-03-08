package com.gg.phoneapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PhoneService {
    @GET("phone")
    Call<CMRespDto<List<Phone>>> findAll();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.55.77:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("phone")
    Call<CMRespDto<List<Phone>>> insert(@Body Phone phone);
}
