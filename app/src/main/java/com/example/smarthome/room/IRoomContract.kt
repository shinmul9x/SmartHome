package com.example.smarthome.room

import com.example.smarthome.api.response.room.RoomItem

interface IRoomContract {
    interface IViewContract {
        fun onGetRoomListSuccess(rooms: ArrayList<RoomItem?>)
        fun onGetRoomListFail()
    }

    interface IPresenterContract {
        fun getRoomList(homeId: String)
    }
}