package com.jubjub.user.ui.view.notice.detail

import android.os.Bundle
import com.jubjub.user.R
import com.jubjub.user.base.BaseActivity
import com.jubjub.user.databinding.ActivityNoticeDetailBinding
import org.koin.android.ext.android.inject

class NoticeDetailActivity : BaseActivity<ActivityNoticeDetailBinding, NoticeDetailViewModel>(R.layout.activity_notice_detail) {

    override val viewModel: NoticeDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

        binding.activity = this


    }

}