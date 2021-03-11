package com.example.jub_jub_android.ui.view.myrentstatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_android.R
import com.example.jub_jub_android.databinding.ActivityMyRentalListBinding
import kotlinx.android.synthetic.main.activity_my_rental_list.*
import kotlinx.android.synthetic.main.layout_pageview.*


class MyRentListActivity : AppCompatActivity() {

    lateinit var pageView : MyRentList_PageView

    lateinit var viewModel: MyRentList_ViewModel

    lateinit var binding: ActivityMyRentalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {

        viewModel = ViewModelProvider(this).get(MyRentList_ViewModel::class.java)
        viewModel.init(applicationContext)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_rental_list)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

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