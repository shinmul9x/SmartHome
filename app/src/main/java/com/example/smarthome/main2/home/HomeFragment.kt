package com.example.smarthome.main2.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.api.response.home.Home
import com.example.smarthome.main2.IMainContract
import com.example.smarthome.main2.MainPresenter
import com.example.smarthome.room.RoomActivity
import com.example.smarthome.utils.HOME_ID_KEY
import com.example.smarthome.utils.HOME_NAME_KEY
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), IMainContract.IViewContract, IHomeAdapterHelper {
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var presenter: IMainContract.IPresenterContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
    }

    override fun onResume() {
        super.onResume()
        presenter.getHomeList()
    }

    private fun initComponent() {
        presenter = MainPresenter(context!!, this)
        homeAdapter = HomeAdapter(ArrayList(), this)
        rv_home_list.apply {
            layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    override fun onGetHomeListSuccess(homes: ArrayList<Home?>) {
        homeAdapter.setList(homes)
    }

    override fun onClickHomeItem(home: Home) {
        val intent = Intent(activity, RoomActivity::class.java)
        intent.putExtra(HOME_ID_KEY, home.homeId)
        intent.putExtra(HOME_NAME_KEY, home.homeName)
        startActivity(intent)
    }
}