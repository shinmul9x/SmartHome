package com.example.smarthome.device

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.device.Device
import kotlinx.android.synthetic.main.item_device.view.*
import java.util.zip.Inflater

class DeviceAdapter(private var list: ArrayList<Device?>) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(list[position]!!)
    }

    fun setList(value: ArrayList<Device?>) {
        list = value
        notifyDataSetChanged()
    }

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var deviceId: String? = ""
        private val deviceType = itemView.tv_device_type
        private val deviceName = itemView.tv_device_name

        fun bind(device: Device) {
            deviceId = device.deviceId
            deviceType.text = device.typeDevice
            deviceName.text = device.nameDevice
        }
    }
}