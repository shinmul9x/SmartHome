package com.example.smarthome.device

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.device.Device
import com.example.smarthome.utils.HOME_ID_KEY
import com.example.smarthome.utils.ROOM_ID_KEY
import com.example.smarthome.utils.ROOM_NAME_KEY
import kotlinx.android.synthetic.main.activity_device.*

class DeviceActivity : AppCompatActivity(), IDeviceContract.IViewContract {
    private lateinit var presenter: IDeviceContract.IPresenterContract
    private var homeId: String? = ""
    private var roomId: String? = ""
    private lateinit var deviceAdapter : DeviceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        initComponents()
        initActions()
    }

    override fun onResume() {
        super.onResume()
        presenter.getDeviceList(homeId!!, roomId!!)
    }

    private fun initComponents() {
        homeId = intent.getStringExtra(HOME_ID_KEY)
        roomId = intent.getStringExtra(ROOM_ID_KEY)
        if (homeId.isNullOrEmpty() || roomId.isNullOrEmpty()) onBackPressed()

        setSupportActionBar(toolbar_device)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Room: ${intent.getStringExtra(ROOM_NAME_KEY)}"

        presenter = DevicePresenter(this, this)

        deviceAdapter = DeviceAdapter(ArrayList())
        rv_device_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = deviceAdapter
        }
    }

    private fun initActions() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onGetDeviceListSuccess(devices: ArrayList<Device?>) {
        deviceAdapter.setList(devices)
    }
}