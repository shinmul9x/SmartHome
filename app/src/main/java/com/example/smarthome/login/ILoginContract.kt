package com.example.smarthome.login

interface ILoginContract {
    interface IViewContract

    interface IPresenterContract {
        fun verifyAccount(username: String, password: String) : Boolean
    }
}