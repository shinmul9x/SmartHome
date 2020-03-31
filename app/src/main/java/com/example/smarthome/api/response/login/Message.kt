package com.example.smarthome.api.response.login

import com.google.gson.annotations.SerializedName

data class Message(

    @field:SerializedName("expires")
    val expires: String? = null,

    @field:SerializedName("role")
    val role: String? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)