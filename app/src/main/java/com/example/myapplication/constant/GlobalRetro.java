package com.example.myapplication.constant;

import android.content.Context;
import android.widget.Toast;


import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalRetro {
    public static RestApi initRetrofit(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        return (RestApi) new Retrofit.Builder().baseUrl(RestApi.BASE_URL3)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                        .connectTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(40, TimeUnit.SECONDS).build()).build().create(RestApi.class);
    }



    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
