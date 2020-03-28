package com.example.smarthome.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthome.R
import com.example.smarthome.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var presenter: ILoginContract.IPresenterContract? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initComponents()
        initActions()
    }

    private fun initComponents() {
        presenter = LoginPresenter(this)
    }

    private fun initActions() {
        btn_login.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            if (presenter?.verifyAccount(username, password)!!) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}