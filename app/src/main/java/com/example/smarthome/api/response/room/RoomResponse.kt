package com.example.smarthome.api.response.room

import com.google.gson.annotations.SerializedName

data class RoomResponse(

	@field:SerializedName("data")
	val data: List<Room?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)