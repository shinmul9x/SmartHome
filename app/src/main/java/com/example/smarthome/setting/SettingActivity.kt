package com.example.smarthome.setting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthome.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    private lateinit var presenter: ISettingContract.IPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initComponents()
        initActions()
    }

    private fun initComponents() {
        setSupportActionBar(toolbar_setting)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = SettingPresenter(this)
        et_host_address.append(presenter.getHostAddress())
    }

    private fun initActions() {
        btn_submit.setOnClickListener {
            val host = et_host_address.text.toString()
            presenter.saveHostAddress(host)
            Toast.makeText(this@SettingActivity, "submit", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}