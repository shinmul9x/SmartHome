package com.example.smarthome.room

import com.example.smarthome.api.response.room.RoomItem

interface IRoomAdapterHelper {
    fun onClickRoomItem(room: RoomItem)
}