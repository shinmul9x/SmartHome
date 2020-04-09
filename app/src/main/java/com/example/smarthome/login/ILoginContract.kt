package com.example.smarthome.login

interface ILoginContract {
    interface IViewContract {
        fun onGetTokenSuccess(token: String)
        fun onGetTokenFail()
    }

    interface IPresenterContract {
        fun verifyAccount(username: String, password: String)
    }
}