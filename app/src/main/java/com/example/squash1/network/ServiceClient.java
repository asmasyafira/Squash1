package com.example.squash1.network;

import com.example.squash1.model.SignInResponse;
import com.example.squash1.model.SignUpResponse;

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

    @FormUrlEncoded
    @POST("exec")
    Call<SignUpResponse> registerUser(
            @Field(value = "sheetName", encoded = true) String sheetName,
            @Field(value = "action", encoded = true) String insert,
            @Field(value = "namaUser", encoded = true) String namaUser,
            @Field(value = "emailUser", encoded = true) String emailUser,
            @Field(value = "passUser", encoded = true) String passUser
    );
}
