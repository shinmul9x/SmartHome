package com.example.smarthome.api.response.device

import com.google.gson.annotations.SerializedName

data class DeviceItem(
    @field:SerializedName("room_id")
    val roomId: Int? = null,
    @field:SerializedName("device_name")
    val deviceName: String? = null,
    @field:SerializedName("device_id")
    val deviceId: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("operation_status")
    val operationStatus: Boolean? = null,
    @field:SerializedName("device_type")
    val deviceType: String? = null,
    @field:SerializedName("id")
    val id: Int? = null
) {
    override fun toString(): String {
        return "$id $deviceType $deviceId $deviceName $name $operationStatus $roomId"
    }
}
