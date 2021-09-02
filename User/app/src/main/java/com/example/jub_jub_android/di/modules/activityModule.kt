package com.example.jub_jub_android.di.modules

import com.example.jub_jub_android.ui.view.login.LoginViewModel
import com.example.jub_jub_android.ui.view.main.MViewModel
import com.example.jub_jub_android.ui.view.main.equipment.EquipmentViewModel
import com.example.jub_jub_android.ui.view.main.myrent.MyRentViewModel
import com.example.jub_jub_android.ui.view.notice.NoticeViewModel
import com.example.jub_jub_android.ui.view.notice.detail.NoticeDetailViewModel
import com.example.jub_jub_android.ui.view.rent_request.RentViewModel
import com.example.jub_jub_android.ui.view.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {

    viewModel { EquipmentViewModel(get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }


    //fragment
    //viewModel { EquipmentStatus_ViewModel() }

    viewModel { MyRentViewModel(get()) }

    viewModel { MViewModel() }

    viewModel { RentViewModel(get()) }
    viewModel { NoticeViewModel() }
    viewModel { NoticeDetailViewModel() }
}