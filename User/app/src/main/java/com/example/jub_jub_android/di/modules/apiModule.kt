package com.example.jub_jub_android.di.modules

import com.example.jub_jub_android.model.network.Api
import com.example.jub_jub_android.model.network.AuthApi
import com.example.jub_jub_android.model.network.EquipmentApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single {
        get<Retrofit>().create(EquipmentApi::class.java)
    }

    single {
        get<Retrofit>().create(AuthApi::class.java)
    }

}