package com.example.smarthome.main2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smarthome.R
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        initComponents()
        initActions()
    }

    private fun initBottomNav() {
        setSupportActionBar(toolbar_main)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_service))
        navController = findNavController(R.id.fragment_host)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_bottom.setupWithNavController(navController)
    }

    private fun initComponents() {
        initBottomNav()
    }

    private fun initActions() {
    }

}