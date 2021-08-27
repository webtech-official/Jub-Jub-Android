package com.example.jub_jub_android.ui.view.notice.detail

import android.os.Bundle
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityNoticeDetailBinding
import org.koin.android.ext.android.inject

class NoticeDetailActivity : BaseActivity<ActivityNoticeDetailBinding, NoticeDetailViewModel>(R.layout.activity_notice_detail) {

    override val viewModel: NoticeDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

        binding.activity = this


    }

}