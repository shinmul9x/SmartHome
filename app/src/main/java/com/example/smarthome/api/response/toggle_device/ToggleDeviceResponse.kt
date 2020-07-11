package com.example.smarthome.api.response.toggle_device

import com.google.gson.annotations.SerializedName

data class ToggleDeviceResponse(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)