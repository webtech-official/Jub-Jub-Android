package com.jubjub.user.di.modules

import com.jubjub.user.model.network.AuthApi
import com.jubjub.user.model.network.EquipmentApi
import com.jubjub.user.model.network.NoticeApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single {
        get<Retrofit>().create(EquipmentApi::class.java)
    }

    single {
        get<Retrofit>().create(AuthApi::class.java)
    }

    single {
        get<Retrofit>().create(NoticeApi::class.java)
    }

}