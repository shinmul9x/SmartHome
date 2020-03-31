package com.example.smarthome.api

import com.example.smarthome.api.response.home.HomeResponse
import com.example.smarthome.api.response.login.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface IApiService {
    @POST("/api/auth/login")
    @FormUrlEncoded
    fun getLoginToken(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("/api/user/home/get-home-list")
    fun getHomeList(
        @Header("x-access-token") token: String
    ): Call<HomeResponse>

}