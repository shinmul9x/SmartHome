package com.example.smarthome.main2

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

    override fun getHomeList() {
        val token = PreferencesUtil().getToken(context)
        ApiManager().getApiService().getHomeList(token).enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.code() == 200) {
                    view.onGetHomeListSuccess(ArrayList(response.body()?.data!!))
                } else {
                    DebugLog().d("${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {

            }
        })
    }

    override fun getServiceList() {

    }
}