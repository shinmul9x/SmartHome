package com.example.smarthome.device

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.device.DeviceItem
import com.example.smarthome.utils.HOME_ID_KEY
import com.example.smarthome.utils.ROOM_ID_KEY
import com.example.smarthome.utils.ROOM_NAME_KEY
import kotlinx.android.synthetic.main.activity_device.*
import java.util.*
import kotlin.collections.ArrayList

class DeviceActivity : AppCompatActivity(), IDeviceContract.IViewContract, IDeviceAdapterHelper {
    private lateinit var presenter: IDeviceContract.IPresenterContract
    private var homeId: String? = ""
    private var roomId: String? = ""
    private lateinit var deviceAdapter: DeviceAdapter

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

        deviceAdapter = DeviceAdapter(this, ArrayList(), this)
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

    override fun onGetDeviceListSuccess(devices: ArrayList<DeviceItem?>) {
        deviceAdapter.setList(devices)
    }

    override fun onGetDeviceListFail() {
        deviceAdapter.setList(ArrayList())
        Toast.makeText(this, "no device", Toast.LENGTH_SHORT).show()
    }

    private val timer = object : CountDownTimer(7000, 1000) {
        override fun onTick(p0: Long) {

        }

        override fun onFinish() {
            onDismissProgressDialog()
            Toast.makeText(
                this@DeviceActivity,
                "Device status cannot be changed, try it...",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onShowProgressDialog() {
        progress_dialog.visibility = View.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        timer.start()
    }

    override fun onDismissProgressDialog() {
        progress_dialog.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        timer.cancel()
    }

    override fun getFragment(): FragmentManager {
        return supportFragmentManager
    }

    override fun onPause() {
        deviceAdapter.setList(ArrayList())
        super.onPause()
    }

    override fun onBackPressed() {
        if (progress_dialog.isVisible) {
            onDismissProgressDialog()
            return
        }
        super.onBackPressed()
    }
}