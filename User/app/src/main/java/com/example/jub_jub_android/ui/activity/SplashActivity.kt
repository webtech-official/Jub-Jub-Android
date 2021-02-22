package com.example.jub_jub_android.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.SharedPref
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.singleton.MyEquipmentListManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPref
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
        sharedPref = SharedPref(applicationContext)

        if(sharedPref.isExist("Id")){
            intent.putExtra("LoginData",  sharedPref.getAccount())
        }
    }
}