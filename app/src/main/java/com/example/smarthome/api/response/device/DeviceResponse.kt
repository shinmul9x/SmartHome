package com.example.smarthome.api.response.device

import com.google.gson.annotations.SerializedName

data class DeviceResponse(

    @field:SerializedName("data")
	val data: List<Device?>? = null,

    @field:SerializedName("success")
	val success: Boolean? = null
)