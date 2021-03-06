package com.example.smarthome.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.home.HomeItem
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
    private var list: ArrayList<HomeItem?>,
    private val listener: IHomeAdapterHelper
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = inflater.inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(viewHolder, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(list[position]!!)
    }

    fun setList(value: ArrayList<HomeItem?>) {
        list = value
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View, private val listener: IHomeAdapterHelper) :
        RecyclerView.ViewHolder(itemView) {
        private val tvHomeName: MaterialTextView = itemView.tv_title
        private val btnDelete: ImageButton = itemView.btn_delete
        private val cvContainer = itemView.cv_home
        private var homeId: String? = ""

        fun bind(home: HomeItem) {
            homeId = home.id.toString()
            tvHomeName.text = home.id.toString()
            cvContainer.setOnClickListener {
                listener.onClickHomeItem(home)
            }

            btnDelete.setOnClickListener {
                //todo call api delete home
            }
        }
    }
}