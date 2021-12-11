package com.jubjub.user.ui.view.notice

import android.os.Bundle
import com.jubjub.user.R
import com.jubjub.user.base.BaseActivity
import com.jubjub.user.databinding.ActivityNoticeBinding
import com.jubjub.user.ui.adapter.recyclerview.NoticeRecyclerViewAdapter
import org.koin.android.ext.android.inject

class NoticeActivity : BaseActivity<ActivityNoticeBinding, NoticeViewModel>(R.layout.activity_notice) {

    override val viewModel: NoticeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = NoticeRecyclerViewAdapter(viewModel.noticeArrayList.value!!)

        binding.noticeRecyclerView.adapter = adapter

    }

}