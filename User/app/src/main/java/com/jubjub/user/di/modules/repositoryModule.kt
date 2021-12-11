package com.jubjub.user.di.modules

import com.jubjub.user.model.local.JubJubStudentDB
import com.jubjub.user.model.local.SharedPref
import com.jubjub.user.model.repository.AuthRepository
import com.jubjub.user.model.repository.EquipmentRepository
import com.jubjub.user.model.repository.MyRentRepository
import com.jubjub.user.model.repository.NoticeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repositoryModule = module {

    single {
        SharedPref(androidContext())
    }

    factory {
        AuthRepository(get(), get())
    }

    single {
        JubJubStudentDB.getInstance(androidContext())
    }

    single { EquipmentRepository(androidContext(), get(), get()) }
    single { get<JubJubStudentDB>().equipmentStatusDAO() }

    single { MyRentRepository(androidContext(), get(), get()) }
    single { get<JubJubStudentDB>().myEquipmentDAO() }

    single { NoticeRepository(androidContext(), get(), get()) }
    single { get<JubJubStudentDB>().noticeDAO() }

}