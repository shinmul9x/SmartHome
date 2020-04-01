package com.example.smarthome.device

import android.content.Context
import com.example.smarthome.api.ApiManager
import com.example.smarthome.api.response.device.DeviceResponse
import com.example.smarthome.utils.DebugLog
import com.example.smarthome.utils.PreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevicePresenter(
    private val context: Context,
    private val view: IDeviceContract.IViewContract
) : IDeviceContract.IPresenterContract {

    override fun getDeviceList(homeId: String, roomId: String) {
        ApiManager().getApiService()
            .getDeviceList(PreferencesUtil().getToken(context), homeId, roomId)
            .enqueue(object : Callback<DeviceResponse> {
                override fun onResponse(
                    call: Call<DeviceResponse>,
                    response: Response<DeviceResponse>
                ) {
                    if (response.code() == 200) {
                        view.onGetDeviceListSuccess(ArrayList(response.body()?.data!!))
                    } else {
                        DebugLog().d("${response.code()}: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DeviceResponse>, t: Throwable) {

                }
            })
    }
}