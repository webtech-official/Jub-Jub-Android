package com.example.jub_jub_android.ui.view.notice

import android.os.Bundle
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityNoticeBinding
import com.example.jub_jub_android.ui.adapter.recyclerview.NoticeRecyclerViewAdapter
import org.koin.android.ext.android.inject

class NoticeActivity : BaseActivity<ActivityNoticeBinding, NoticeViewModel>(R.layout.activity_notice) {

    override val viewModel: NoticeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = NoticeRecyclerViewAdapter(arrayListOf(1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,0))

        binding.noticeRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()


    }

}