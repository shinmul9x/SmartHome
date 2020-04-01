package com.example.smarthome.api.response.room

import com.google.gson.annotations.SerializedName

data class Room(

	@field:SerializedName("room_id")
	val roomId: String? = null,

	@field:SerializedName("room_name")
	val roomName: String? = null
)