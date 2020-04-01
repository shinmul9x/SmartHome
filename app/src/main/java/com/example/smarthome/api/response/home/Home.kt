package com.example.smarthome.api.response.home

import com.google.gson.annotations.SerializedName

data class Home(

	@field:SerializedName("home_name")
	val homeName: String? = null,

	@field:SerializedName("home_id")
	val homeId: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)