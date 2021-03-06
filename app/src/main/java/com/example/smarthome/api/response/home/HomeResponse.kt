package com.example.smarthome.api.response.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("home")
	val home: List<HomeItem?>? = null
)