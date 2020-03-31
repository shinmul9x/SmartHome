package com.example.smarthome.main2.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.R
import com.example.smarthome.main2.IMainContract
import com.example.smarthome.main2.MainPresenter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), IMainContract.IViewContract {
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
        homeAdapter = HomeAdapter(context!!, ArrayList())
        rv_home_list.apply {
            layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    override fun onGetHomeListSuccess(homes: ArrayList<Home>) {
        homeAdapter.setList(homes)
    }
}