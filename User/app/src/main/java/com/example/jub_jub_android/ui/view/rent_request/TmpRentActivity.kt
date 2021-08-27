package com.example.jub_jub_android.ui.view.rent_request

import android.os.Bundle
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityRentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TmpRentActivity: BaseActivity<ActivityRentBinding, RentViewModel>(R.layout.activity_rent_t) {

    override val viewModel: RentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}