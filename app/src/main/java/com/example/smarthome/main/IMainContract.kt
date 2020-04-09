package com.example.smarthome.main

import com.example.smarthome.api.response.home.HomeItem

interface IMainContract {
    interface IPresenterContract {
        fun getUserInfo()
    }

    interface IViewContract {
        fun onGetHomeListSuccess(homes: ArrayList<HomeItem?>)
        fun onGetHomeListFail()
    }
}
