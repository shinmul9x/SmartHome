package com.example.smarthome.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.home.HomeItem
import com.example.smarthome.room.RoomActivity
import com.example.smarthome.utils.HOME_ID_KEY
import com.example.smarthome.utils.HOME_NAME_KEY
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), IMainContract.IViewContract, IHomeAdapterHelper {
    private lateinit var presenter: IMainContract.IPresenterContract
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initComponents()
        initActions()
    }

    private fun initComponents() {
        setSupportActionBar(toolbar_main)
        presenter = MainPresenter(this, this)
        homeAdapter = HomeAdapter(ArrayList(), this)
        rv_home_list.apply {
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    private fun initActions() {

    }

    override fun onResume() {
        super.onResume()
        presenter.getUserInfo()
    }

    override fun onGetHomeListSuccess(homes: ArrayList<HomeItem?>) {
        homeAdapter.setList(homes)
    }

    override fun onGetHomeListFail() {
        homeAdapter.setList(ArrayList())
        Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show()
    }

    override fun onClickHomeItem(home: HomeItem) {
        val intent = Intent(this, RoomActivity::class.java)
        intent.putExtra(HOME_ID_KEY, home.id.toString())
        intent.putExtra(HOME_NAME_KEY, home.id.toString())
        startActivity(intent)
    }
}