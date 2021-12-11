package com.jubjub.user.di.modules

import com.jubjub.user.ui.view.login.LoginViewModel
import com.jubjub.user.ui.view.main.MViewModel
import com.jubjub.user.ui.view.main.equipment.EquipmentViewModel
import com.jubjub.user.ui.view.main.myrent.MyRentViewModel
import com.jubjub.user.ui.view.notice.NoticeViewModel
import com.jubjub.user.ui.view.notice.detail.NoticeDetailViewModel
import com.jubjub.user.ui.view.rent_request.RentViewModel
import com.jubjub.user.ui.view.signup.SignUpViewModel
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
    viewModel { NoticeViewModel(get()) }
    viewModel { NoticeDetailViewModel() }
}