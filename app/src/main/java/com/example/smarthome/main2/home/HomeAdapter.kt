package com.example.smarthome.main2.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.row_home.view.*

class HomeAdapter(
    private val context: Context,
    private var list: ArrayList<Home>
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = inflater.inflate(R.layout.row_home, parent, false)
        return HomeViewHolder(context, viewHolder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setList(value: ArrayList<Home>) {
        list = value
        notifyDataSetChanged()
    }

    class HomeViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvHomeName: MaterialTextView = itemView.tv_title
        private var btnDelete: ImageButton = itemView.btn_delete
        private var cvContainer = itemView.cv_home
        private var homeId: Int? = null

        fun bind(home: Home) {
            homeId = home.id
            tvHomeName.text = home.name
            cvContainer.setOnClickListener {
                Toast.makeText(context, "$homeId", Toast.LENGTH_SHORT).show()
            }
        }
    }
}