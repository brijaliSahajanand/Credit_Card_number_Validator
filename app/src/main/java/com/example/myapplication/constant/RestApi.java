package com.example.myapplication.constant;


import com.example.myapplication.model.BinList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {
    public static final String BASE_URL3 = "https://lookup.binlist.net";
    public static final String intentdata = "/{id}";

    @GET("/{id}")
    Call<BinList> DoBinList(@Path("id") String str);
}
