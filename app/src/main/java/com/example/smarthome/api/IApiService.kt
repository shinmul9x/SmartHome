package com.example.smarthome.api

import com.example.smarthome.api.response.device.DeviceResponse
import com.example.smarthome.api.response.home.HomeResponse
import com.example.smarthome.api.response.login.LoginResponse
import com.example.smarthome.api.response.room.RoomResponse
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

    @GET("/api/user/room/get-room-list/home/{home_id}")
    fun getRoomList(
        @Header("x-access-token") token: String,
        @Path(value = "home_id", encoded = true) homeId: String
    ): Call<RoomResponse>

    @GET("/api/user/room/get-room-list/home/{home_id}/room/{room_id}")
    fun getDeviceList(
        @Header("x-access-token") token: String,
        @Path(value = "home_id", encoded = true) homeId: String,
        @Path(value = "room_id", encoded = true) roomId: String
    ): Call<DeviceResponse>
}