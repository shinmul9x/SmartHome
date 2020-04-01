package com.example.smarthome.room

import com.example.smarthome.api.response.room.Room

interface IRoomContract {
    interface IViewContract {
        fun onGetRoomListSuccess(rooms: ArrayList<Room?>)
    }

    interface IPresenterContract {
        fun getRoomList(homeId: String)
    }
}