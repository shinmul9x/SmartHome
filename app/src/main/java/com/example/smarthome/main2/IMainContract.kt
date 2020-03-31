package com.example.smarthome.main2

import com.example.smarthome.main2.home.Home

interface IMainContract {
    interface IPresenterContract {
        fun getHomeList()
        fun getServiceList()
    }

    interface IViewContract {
        fun onGetHomeListSuccess(homes: ArrayList<Home>)
    }
}