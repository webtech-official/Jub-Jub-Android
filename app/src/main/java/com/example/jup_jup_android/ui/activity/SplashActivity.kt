package com.example.jup_jup_android.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        startApp()
    }

    private fun startApp() {
        ItemStatusListManager.initItemStatusList(applicationContext, 10)

        startActivity(Intent(applicationContext, LoginActivity ::class.java))
    }
}