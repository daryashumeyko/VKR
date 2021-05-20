package com.example.vetsertification.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InfoManagerService {
    /*@Headers({
        "Content-Type:application/json",
        "Authorization:key=Lhghhkvhvhffgv"
    })*/

    @POST("auth")
    @FormUrlEncoded
    Call<Response> signIn(@Field("email") String email, @Field("password") String password);
}
