package com.example.smarthome.api.response.room

import com.google.gson.annotations.SerializedName

data class RoomResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("room")
	val room: List<RoomItem?>? = null
)