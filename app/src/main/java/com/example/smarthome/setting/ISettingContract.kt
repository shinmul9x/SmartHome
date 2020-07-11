package com.example.smarthome.setting

interface ISettingContract {
    interface IPresenterContract {
        fun saveHostAddress(host: String)
        fun getHostAddress(): String
    }
}