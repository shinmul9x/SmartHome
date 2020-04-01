package com.example.smarthome.device

import com.example.smarthome.api.response.device.Device

interface IDeviceContract {
    interface IPresenterContract {
        fun getDeviceList(homeId: String, roomId: String)
    }

    interface IViewContract {
        fun onGetDeviceListSuccess(devices: ArrayList<Device?>)
    }
}