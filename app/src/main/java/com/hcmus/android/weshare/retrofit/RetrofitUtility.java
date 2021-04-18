package com.hcmus.android.weshare.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtility extends Application {

    private static final RetrofitUtility INSTANCE = new RetrofitUtility();
    private static Retrofit retrofit;
    private final String TAG = getClass().getSimpleName();
    private final String BASE_URL = "https://weshare-springboot.herokuapp.com/";

    private RetrofitUtility() {
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static RetrofitUtility getInstance() {
        return INSTANCE;
    }

    public Retrofit getRetrofitClient() {
        return retrofit;
    }
}
