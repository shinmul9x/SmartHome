package com.example.smarthome.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IApiService {
    @POST("/api/auth/login")
    @FormUrlEncoded
    fun getLoginToken(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}