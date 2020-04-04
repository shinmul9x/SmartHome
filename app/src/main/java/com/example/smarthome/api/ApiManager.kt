package com.example.smarthome.api

import android.content.Context
import com.example.smarthome.utils.PreferencesUtil


class ApiManager {
//    private fun getURL() = "http://sv-procon.uet.vnu.edu.vn:3000"

    private fun getURL(context: Context) = PreferencesUtil().getHostAddress(context)
    fun getApiService(context: Context): IApiService {
        return ApiClient().getClient(getURL(context))!!.create(IApiService::class.java)
    }
}