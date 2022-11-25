package com.example.myapplication.retrofit;

import com.example.myapplication.model.Example;
import com.example.myapplication.model.Pro_IPModel;
import com.example.myapplication.model.TraficLimitResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {
    @POST("api/setting")
    Call<Example> setting_api(@Body Map<String, String> body);


    @POST("api/setting")
    @FormUrlEncoded
    Call<Example> setting_api(@Field("package") String i,
                              @Field("country") String country,
                              @Field("state") String status,
                              @Field("city") String city);


    @GET("?fields=61439")
    Call<Pro_IPModel> getipdata();

    @DELETE()
    Call<TraficLimitResponse> Call_Delete_Trafic(@Url String url);

    @POST()
    Call<TraficLimitResponse> Call_Add_Trafic(@Url String url);

}