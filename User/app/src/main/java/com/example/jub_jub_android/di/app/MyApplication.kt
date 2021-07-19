package com.example.jub_jub_android.di.app

import android.app.Application
import com.example.jub_jub_android.di.modules.activityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    companion object {
        const val BASE_URL = "http://15.165.97.179:8080/v2/"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                activityModule
            )
        }
    }
}