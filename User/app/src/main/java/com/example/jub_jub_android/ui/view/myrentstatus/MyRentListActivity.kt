package com.example.jub_jub_android.ui.view.myrentstatus

import android.os.Bundle
import android.widget.Toast
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityMyRentalListBinding
import kotlinx.android.synthetic.main.activity_my_rental_list.*
import kotlinx.android.synthetic.main.layout_pageview.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyRentListActivity : BaseActivity<ActivityMyRentalListBinding, MyRentList_ViewModel>(
    R.layout.activity_my_rental_list
) {

    lateinit var pageView : MyRentList_PageView

    override val viewModel: MyRentList_ViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {

        viewModel.init(applicationContext)

        binding.activity = this

        setPageView()

        refreshLayout.setOnRefreshListener {
            refresh()
        }

        viewModel.showStatus.observe(this, {
            viewModel.processShowList(it)
        })

        viewModel.list.observe(this, {
            pageView.syncPage()
        })

    }

    private fun refresh() {
        refreshLayout.isRefreshing = true
        viewModel.getMyEquipmentData(applicationContext)
        Toast.makeText(applicationContext, "새로고침 완료", Toast.LENGTH_SHORT).show()
        refreshLayout.isRefreshing = false
    }

    private fun setPageView() {
        pageView = MyRentList_PageView(applicationContext, pageView_MyRentalListActivity, viewModel)
        pageView.initViewPager()
    }


    fun back(){
        viewModel.processShowList("전체")
        finish()
    }

    override fun onBackPressed() = back()
}