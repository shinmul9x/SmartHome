package com.example.smarthome.device

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.api.ApiManager
import com.example.smarthome.api.response.device.DeviceContent
import com.example.smarthome.api.response.device.DeviceItem
import com.example.smarthome.api.response.toggle_device.ToggleDeviceResponse
import com.example.smarthome.utils.DebugLog
import com.example.smarthome.utils.FireStoreReference
import com.example.smarthome.utils.PreferencesUtil
import com.github.mikephil.charting.data.Entry
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.android.synthetic.main.item_device.view.*
import kotlinx.android.synthetic.main.popup_chart_room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import com.github.mikephil.charting.data.LineData
import androidx.core.content.ContextCompat
import android.graphics.DashPathEffect
import com.example.smarthome.R
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.popup_chart_room.view.*


class DeviceAdapter(
    private val context: Context,
    private var list: ArrayList<DeviceItem?>,
    private val listener: IDeviceAdapterHelper
) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(context, listener, itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(list[position]!!)
    }

    fun setList(value: ArrayList<DeviceItem?>) {
        list = value
        notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: DeviceViewHolder) {
        holder.stopListenFirestore()
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewAttachedToWindow(holder: DeviceViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.startListenFirestore()
    }

    class DeviceViewHolder(
        val context: Context,
        private val listener: IDeviceAdapterHelper,
        itemView: View
    ) :
        RecyclerView.ViewHolder(itemView) {
        private var deviceId: String? = ""
        private var id: Int? = 0
        private var roomId: Int? = 0
        private val deviceType = itemView.tv_device_type
        private val deviceName = itemView.tv_device_name
        private val name = itemView.tv_name
        private val startAt = itemView.tv_start_at
        private val totalPower = itemView.tv_total_power
        private val switchDevice = itemView.sw_toggle_device
        private var fireStoreListener: ListenerRegistration? = null
        private val btnChart = itemView.btn_show_chart

        @SuppressLint("SetTextI18n", "InflateParams")
        fun bind(device: DeviceItem) {
            deviceId = device.deviceId
            id = device.id
            roomId = device.roomId
            deviceType.text = device.deviceType
            deviceName.text = device.deviceName
            name.text = device.name
            switchDevice.isChecked = device.operationStatus!!

            switchDevice.setOnClickListener {
                listener.onShowProgressDialog()
                DebugLog().d("Call api toggle device")
                ApiManager().getApiService(context)
                    .toggleDevice(PreferencesUtil().getToken(context), id!!, switchDevice.isChecked)
                    .enqueue(object :
                        Callback<ToggleDeviceResponse> {
                        override fun onResponse(
                            call: Call<ToggleDeviceResponse>,
                            response: Response<ToggleDeviceResponse>
                        ) {
                            if (response.code() == 200) {
                                if (response.body()?.success == true) {
//                                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                                    DebugLog().d("call api control device success")
                                } else if (response.body()?.message.equals("invalid token")) {
                                    Toast.makeText(
                                        context,
                                        "invalid token, back switch",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<ToggleDeviceResponse>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "call api fail, back switch",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

                switchDevice.isChecked = !switchDevice.isChecked
            }

            // get content from firestore
            FireStoreReference().getInstance(context).whereEqualTo("device_id", deviceId).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (doc in task.result!!) {
                            val data = doc.toObject(DeviceContent::class.java)
                            setContent(data)
                        }
                    }
                }

            btnChart.setOnClickListener {
                //todo get data
                ChartDialog(ArrayList()).showNow(listener.getFragment(), "${deviceType.text}: ${deviceName.text}")
            }


        }

        private fun setContent(data: DeviceContent) {
            if (data.operation_status != null) {
                if (switchDevice.isChecked != data.operation_status) {
                    listener.onDismissProgressDialog()
                }
                switchDevice.isChecked = data.operation_status
            }
            if (data.device_type != null) {
                name.text = data.device_type
            }
        }

        fun stopListenFirestore() {
            fireStoreListener?.remove()
            DebugLog().d("${deviceName.text} stop listening firestore")
        }

        fun startListenFirestore() {
            fireStoreListener =
                FireStoreReference().getInstance(context).whereEqualTo("device_id", deviceId)
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        if (firebaseFirestoreException != null) {
                            DebugLog().d("Listen firestore change fail")
                            return@addSnapshotListener
                        }

                        for (dc in querySnapshot!!.documentChanges) {
                            val data = dc.document.toObject(DeviceContent::class.java)
                            setContent(data)
                        }
                    }

            DebugLog().d("${deviceName.text} start listening firestore")
        }

    }
}