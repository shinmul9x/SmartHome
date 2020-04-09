package com.example.smarthome.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthome.R
import com.example.smarthome.main.Main2Activity
import com.example.smarthome.setting.SettingActivity
import com.example.smarthome.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginContract.IViewContract {
    private lateinit var presenter: ILoginContract.IPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initComponents()
        initActions()
    }

    private fun initComponents() {
        setSupportActionBar(toolbar_login)
        presenter = LoginPresenter(this, this)
    }

    private fun initActions() {
        btn_login.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            presenter.verifyAccount(username, password)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_menu) {
            startActivity(Intent(this, SettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onGetTokenSuccess(token: String) {
        PreferencesUtil().saveToken(this, token)
        startActivity(Intent(this, Main2Activity::class.java))
    }

    override fun onGetTokenFail() {
        //todo
    }
}