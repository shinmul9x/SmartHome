package com.example.smarthome.api.response.home

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("homes")
	val homes: List<HomeItem?>? = null
)