package com.example.smarthome.setting

import android.content.Context
import com.example.smarthome.utils.PreferencesUtil

class SettingPresenter(private val context: Context) : ISettingContract.IPresenterContract {
    override fun getGetHostAddress(): String {
        return PreferencesUtil().getHostAddress(context)
    }

    override fun saveHostAddress(host: String) {
        PreferencesUtil().saveHostAddress(context, host)
    }
}