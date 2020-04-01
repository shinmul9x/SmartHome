package com.example.smarthome.api.response.device

import com.google.gson.annotations.SerializedName

data class Device(

	@field:SerializedName("current_date_setting")
	val currentDateSetting: String? = null,

	@field:SerializedName("type_device")
	val typeDevice: String? = null,

	@field:SerializedName("group_code")
	val groupCode: Int? = null,

	@field:SerializedName("identification_number")
	val identificationNumber: String? = null,

	@field:SerializedName("instance_code")
	val instanceCode: Int? = null,

	@field:SerializedName("business_facility_code")
	val businessFacilityCode: String? = null,

	@field:SerializedName("remote_control_setting")
	val remoteControlSetting: String? = null,

	@field:SerializedName("fault_status")
	val faultStatus: String? = null,

	@field:SerializedName("manufacture_fault_code")
	val manufactureFaultCode: String? = null,

	@field:SerializedName("product_code")
	val productCode: Any? = null,

	@field:SerializedName("install_location")
	val installLocation: Int? = null,

	@field:SerializedName("product_number")
	val productNumber: String? = null,

	@field:SerializedName("product_date")
	val productDate: String? = null,

	@field:SerializedName("property_map")
	val propertyMap: String? = null,

	@field:SerializedName("fault_description")
	val faultDescription: String? = null,

	@field:SerializedName("manufacture_code")
	val manufactureCode: String? = null,

	@field:SerializedName("power_saving_operation_setting")
	val powerSavingOperationSetting: String? = null,

	@field:SerializedName("device_id")
	val deviceId: String? = null,

	@field:SerializedName("measured_instantaneous_power_consumption")
	val measuredInstantaneousPowerConsumption: String? = null,

	@field:SerializedName("name_device")
	val nameDevice: String? = null,

	@field:SerializedName("current_time_setting")
	val currentTimeSetting: String? = null,

	@field:SerializedName("current_limit_setting")
	val currentLimitSetting: String? = null,

	@field:SerializedName("power_limit_setting")
	val powerLimitSetting: String? = null,

	@field:SerializedName("standard_version_information")
	val standardVersionInformation: String? = null,

	@field:SerializedName("measured_cumulative_power_consumption")
	val measuredCumulativePowerConsumption: String? = null,

	@field:SerializedName("class_code")
	val classCode: Int? = null,

	@field:SerializedName("cumulative_setting")
	val cumulativeSetting: String? = null
)