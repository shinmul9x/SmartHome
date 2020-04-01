package com.example.smarthome.room

import android.content.Context
import com.example.smarthome.api.ApiManager
import com.example.smarthome.api.response.room.RoomResponse
import com.example.smarthome.utils.DebugLog
import com.example.smarthome.utils.PreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomPresenter(private val context: Context, private val view: IRoomContract.IViewContract) :
    IRoomContract.IPresenterContract {
    override fun getRoomList(homeId: String) {
        ApiManager().getApiService()
            .getRoomList(PreferencesUtil().getToken(context), homeId)
            .enqueue(object : Callback<RoomResponse> {
                override fun onResponse(
                    call: Call<RoomResponse>,
                    response: Response<RoomResponse>
                ) {
                    if (response.code() == 200) {
                        view.onGetRoomListSuccess(ArrayList(response.body()?.data!!))
                    } else {
                        DebugLog().d("${response.code()}: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<RoomResponse>, t: Throwable) {

                }
            })
    }
}