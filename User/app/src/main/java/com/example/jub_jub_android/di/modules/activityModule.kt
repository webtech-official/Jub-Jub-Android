package com.example.jub_jub_android.di.modules

import com.example.jub_jub_android.ui.view.equipment_status.EquipmentStatus_ViewModel
import com.example.jub_jub_android.ui.view.login.LoginViewModel
import com.example.jub_jub_android.ui.view.main.MViewModel
import com.example.jub_jub_android.ui.view.myrentstatus.MyRentList_ViewModel
import com.example.jub_jub_android.ui.view.notice.NoticeViewModel
import com.example.jub_jub_android.ui.view.notice.detail.NoticeDetailViewModel
import com.example.jub_jub_android.ui.view.rent_request.RentViewModel
import com.example.jub_jub_android.ui.view.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {

    viewModel { EquipmentStatus_ViewModel() }

    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }


    //fragment
    //viewModel { EquipmentStatus_ViewModel() }

    viewModel { MyRentList_ViewModel() }

    viewModel { MViewModel() }

    viewModel { RentViewModel() }
    viewModel { NoticeViewModel() }
    viewModel { NoticeDetailViewModel() }
}