package com.example.jub_jub_android.di.app

import android.app.Application
import com.example.jub_jub_android.di.modules.activityModule
import com.example.jub_jub_android.di.modules.apiModule
import com.example.jub_jub_android.di.modules.networkModule
import com.example.jub_jub_android.di.modules.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    companion object {
        const val BASE_URL = "http://10.120.72.245:8081/v2/"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                    activityModule,
                    apiModule,
                    networkModule,
                    repositoryModule
            )
        }
    }
}