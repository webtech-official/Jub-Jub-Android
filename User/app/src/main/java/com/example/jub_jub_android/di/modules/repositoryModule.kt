package com.example.jub_jub_android.di.modules

import com.example.jub_jub_android.model.local.JubJubStudentDB
import com.example.jub_jub_android.model.local.SharedPref
import com.example.jub_jub_android.model.repository.AuthRepository
import com.example.jub_jub_android.model.repository.EquipmentRepository
import com.example.jub_jub_android.model.repository.MyRentRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repositoryModule = module {

    single {
        SharedPref(androidContext())
    }

    factory {
        AuthRepository(get(), get())
    }

    single { EquipmentRepository(androidContext(), get(), get()) }
    single { JubJubStudentDB.getInstance(androidContext())?.equipmentStatusDAO() }

    single { MyRentRepository(androidContext(), get()) }
    single { JubJubStudentDB.getInstance(androidContext())?.myEquipmentDAO() }


}