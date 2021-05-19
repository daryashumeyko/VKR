package com.example.vetsertification.api;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoManager {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.1.162")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final InfoManagerService infoManager = retrofit.create(InfoManagerService.class);

    public static Call<Response> signIn(String email, String password) {
        return infoManager.signIn(email, password);
    }
}