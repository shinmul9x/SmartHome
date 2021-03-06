package com.example.smarthome.main

import android.content.Context
import com.example.smarthome.api.ApiManager
import com.example.smarthome.api.response.home.HomeResponse
import com.example.smarthome.utils.DebugLog
import com.example.smarthome.utils.PreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val context: Context, private val view: IMainContract.IViewContract) :
    IMainContract.IPresenterContract {

    override fun getUserInfo() {
        val token = PreferencesUtil().getToken(context)
        ApiManager().getApiService(context).getHomeList(token)
            .enqueue(object : Callback<HomeResponse> {
                override fun onResponse(
                    call: Call<HomeResponse>,
                    response: Response<HomeResponse>
                ) {
                    if (response.code() == 200) {
                        val homes = response.body()?.home
                        if (homes == null) {
                            view.onGetHomeListFail()
                            return
                        }
                        view.onGetHomeListSuccess(ArrayList(homes))
                    } else {
                        DebugLog().d("${response.code()}: ${response.message()}")
                        view.onGetHomeListFail()
                    }
                }

                override fun onFailure(call: Call<HomeResponse>, t: Throwable) {

                }
            })
    }
}