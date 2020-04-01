package com.example.smarthome.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.room.Room
import kotlinx.android.synthetic.main.item_room.view.*

class RoomAdapter(private var list: ArrayList<Room?>, private val listener: IRoomAdapterHelper) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(list[position]!!)
    }

    fun setList(value: ArrayList<Room?>) {
        list = value
        notifyDataSetChanged()
    }

    class RoomViewHolder(itemView: View, private val listener: IRoomAdapterHelper) :
        RecyclerView.ViewHolder(itemView) {
        private var roomId: String? = ""
        private val tvRoomName = itemView.tv_title
        private val cvRoomItem = itemView.cv_room
        private val btnDelete = itemView.btn_delete

        fun bind(room: Room) {
            roomId = room.roomId
            tvRoomName.text = room.roomName
            cvRoomItem.setOnClickListener {
                listener.onClickRoomItem(room)
            }
            btnDelete.setOnClickListener {
                // todo call api delete room
            }
        }

    }
}