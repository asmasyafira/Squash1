package com.example.squash1.network;

import com.example.squash1.model.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceClient {

    @FormUrlEncoded
    @POST("exec")
    Call<SignInResponse> signinUser(
        @Field(value = "sheetName", encoded = true)String sheetName,
        @Field(value = "action", encoded = true)String login,
        @Field(value = "idUser", encoded = true)String idUser,
        @Field(value = "pass", encoded = true)String pass

    );
}
