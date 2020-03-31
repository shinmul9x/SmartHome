package com.example.smarthome.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smarthome.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

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
        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_room,
                R.id.nav_device,
                R.id.nav_service,
                R.id.nav_auth_service
            ), drawer_main
        )
        navController = findNavController(R.id.fragment_host)
        setupActionBarWithNavController(navController, appBarConfig)
        nav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

}