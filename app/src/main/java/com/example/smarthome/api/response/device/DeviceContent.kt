package com.example.smarthome.api.response.device

import com.google.gson.annotations.SerializedName

data class DeviceContent(
    val operation_status: Boolean? = null,
    val device_type: String? = null
)