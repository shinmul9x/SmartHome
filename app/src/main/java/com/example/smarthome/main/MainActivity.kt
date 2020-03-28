package com.example.smarthome.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthome.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initActions()
    }

    private fun initComponents() {
        initDrawer()
    }

    private fun initActions() {

    }

    private fun initDrawer() {
        setSupportActionBar(toolbar_main)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout_main,
            toolbar_main,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout_main.addDrawerListener(toggle)
        toggle.syncState()
    }


}