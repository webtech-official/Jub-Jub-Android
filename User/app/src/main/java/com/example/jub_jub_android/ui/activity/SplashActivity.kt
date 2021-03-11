package com.example.jub_jub_android.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.SharedPref

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startApp()
    }

    private fun startApp() {
         //MyEquipmentListManager.setDummyDataList(applicationContext, 100)
        val intent = Intent(applicationContext, LoginActivity ::class.java)
        init(intent)
        startActivity(intent)
        finish()
    }

    private fun init(intent: Intent) {
        val sharedPref = SharedPref(applicationContext)

        if(sharedPref.isExist("Id")){
            intent.putExtra("LoginData",  sharedPref.getAccount())
        }
    }
}