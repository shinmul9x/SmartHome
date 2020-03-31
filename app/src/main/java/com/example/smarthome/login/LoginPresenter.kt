package com.example.smarthome.login

import android.content.Context
import com.example.smarthome.api.ApiManager
import com.example.smarthome.api.response.login.LoginResponse
import com.example.smarthome.utils.DebugLog
import com.example.smarthome.utils.PreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val context: Context) : ILoginContract.IPresenterContract {
    override fun verifyAccount(username: String, password: String): Boolean {
        ApiManager().getApiService()?.getLoginToken(username, password)
            ?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.code() == 200) {
                        val token = response.body()?.message?.token
                        val typeUser = response.body()?.message?.role
                        if (typeUser.equals("user")) {
                            PreferencesUtil().putToken(context, token!!)
                        }
                    } else {
                        DebugLog().d("${response.code()}: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    DebugLog().d("call api fail")
                }
            })

        return PreferencesUtil().getToken(context).isNotEmpty()
    }
}