package com.example.smarthome.api.response.login

import com.google.gson.annotations.SerializedName

data class Login2Response(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("token")
    val token: String? = null
)