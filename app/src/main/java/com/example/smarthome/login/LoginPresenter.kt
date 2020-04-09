package com.example.smarthome.login

import android.content.Context
import com.example.smarthome.api.ApiManager
import com.example.smarthome.api.response.login.Login2Response
import com.example.smarthome.utils.DebugLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(
    private val context: Context,
    private val view: ILoginContract.IViewContract
) : ILoginContract.IPresenterContract {
    override fun verifyAccount(username: String, password: String) {
        ApiManager().getApiService(context).getLoginToken(username)
            .enqueue(object : Callback<Login2Response> {
                override fun onResponse(
                    call: Call<Login2Response>,
                    response: Response<Login2Response>
                ) {
                    if (response.code() == 200) {
                        val token = response.body()?.token
                        if (token.isNullOrEmpty()) {
                            view.onGetTokenFail()
                        } else {
                            view.onGetTokenSuccess(token)
                        }
                    } else {
                        DebugLog().d("${response.code()}: ${response.message()}")
                        view.onGetTokenFail()
                    }
                }

                override fun onFailure(call: Call<Login2Response>, t: Throwable) {
                    DebugLog().d("call api fail")
                }
            })
    }
}