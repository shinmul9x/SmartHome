package com.example.smarthome.api.response.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(

	@field:SerializedName("data")
	val data: List<Home?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)