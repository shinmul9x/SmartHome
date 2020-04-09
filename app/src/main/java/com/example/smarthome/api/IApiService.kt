package com.example.smarthome.api

import com.example.smarthome.api.response.device.DeviceResponse
import com.example.smarthome.api.response.home.HomeResponse
import com.example.smarthome.api.response.home.UserInfoResponse
import com.example.smarthome.api.response.login.Login2Response
import com.example.smarthome.api.response.login.LoginResponse
import com.example.smarthome.api.response.room.RoomResponse
import retrofit2.Call
import retrofit2.http.*

interface IApiService {
    @POST("/api/login")
    @FormUrlEncoded
    fun getLoginToken(
        @Field("username") username: String
    ): Call<Login2Response>

    @GET("/api/get-user-info")
    fun getUserInfo(
        @Header("x-access-token") token: String
    ): Call<UserInfoResponse>

    @GET("/api/get-home")
    fun getHomeList(
        @Header("x-access-token") token: String
    ): Call<HomeResponse>

    @GET("/api/get-room/home/{home_id}")
    fun getRoomList(
        @Header("x-access-token") token: String,
        @Path(value = "home_id", encoded = true) homeId: String
    ): Call<RoomResponse>

    @GET("/api/user/device/get-device-list/home/{home_id}/room/{room_id}")
    fun getDeviceList(
        @Header("x-access-token") token: String,
        @Path(value = "home_id", encoded = true) homeId: String,
        @Path(value = "room_id", encoded = true) roomId: String
    ): Call<DeviceResponse>
}