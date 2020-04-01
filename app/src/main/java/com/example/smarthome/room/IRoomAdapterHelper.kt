package com.example.smarthome.room

import com.example.smarthome.api.response.room.Room

interface IRoomAdapterHelper {
    fun onClickRoomItem(room: Room)
}