package com.example.smarthome.room

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.room.Room
import com.example.smarthome.device.DeviceActivity
import com.example.smarthome.utils.HOME_ID_KEY
import com.example.smarthome.utils.HOME_NAME_KEY
import com.example.smarthome.utils.ROOM_ID_KEY
import com.example.smarthome.utils.ROOM_NAME_KEY
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity(), IRoomContract.IViewContract, IRoomAdapterHelper {
    private lateinit var presenter: IRoomContract.IPresenterContract
    private lateinit var roomAdapter: RoomAdapter
    private var homeId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initComponents()
        initActions()
    }

    private fun initComponents() {
        homeId = intent.getStringExtra(HOME_ID_KEY)
        if (homeId.isNullOrEmpty()) onBackPressed()

        setSupportActionBar(toolbar_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val homeName = intent.getStringExtra(HOME_NAME_KEY)
        supportActionBar?.title = "Home: $homeName"

        presenter = RoomPresenter(this, this)
        roomAdapter = RoomAdapter(ArrayList(), this)
        rv_room_list.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            adapter = roomAdapter
        }
    }

    private fun initActions() {
        btn_add_room.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getRoomList(homeId!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onGetRoomListSuccess(rooms: ArrayList<Room?>) {
        roomAdapter.setList(rooms)
    }

    override fun onClickRoomItem(room: Room) {
        val intent = Intent(this, DeviceActivity::class.java)
        intent.putExtra(HOME_ID_KEY, homeId!!)
        intent.putExtra(ROOM_ID_KEY, room.roomId)
        intent.putExtra(ROOM_NAME_KEY, room.roomName)
        startActivity(intent)
    }
}