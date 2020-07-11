package com.example.smarthome.api.response.device

import com.google.gson.annotations.SerializedName

data class DeviceResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("device")
	val device: List<DeviceItem?>? = null
)
