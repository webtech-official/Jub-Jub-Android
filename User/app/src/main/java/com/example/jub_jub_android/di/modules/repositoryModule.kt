package com.example.jub_jub_android.di.modules

import com.example.jub_jub_android.model.local.SharedPref
import com.example.jub_jub_android.model.repository.AuthRepository
import io.reactivex.Single
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repositoryModule = module {

    single {
        SharedPref(androidContext())
    }

    factory {
        AuthRepository(get(), get())
    }

}