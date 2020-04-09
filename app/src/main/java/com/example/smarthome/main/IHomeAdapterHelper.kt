package com.example.smarthome.main

import com.example.smarthome.api.response.home.HomeItem

interface IHomeAdapterHelper {
    fun onClickHomeItem(home: HomeItem)
}