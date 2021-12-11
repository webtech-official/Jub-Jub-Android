package com.jubjub.user.di.app

import android.app.Application
import com.jubjub.user.di.modules.activityModule
import com.jubjub.user.di.modules.apiModule
import com.jubjub.user.di.modules.networkModule
import com.jubjub.user.di.modules.repositoryModule
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