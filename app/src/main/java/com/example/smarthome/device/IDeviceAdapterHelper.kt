package com.example.smarthome.device

import androidx.fragment.app.FragmentManager

interface IDeviceAdapterHelper {
    fun onShowProgressDialog()
    fun onDismissProgressDialog()
    fun getFragment() : FragmentManager
}
