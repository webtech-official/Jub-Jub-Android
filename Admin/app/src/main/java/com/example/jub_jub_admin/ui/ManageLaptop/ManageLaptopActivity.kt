package com.example.jub_jub_admin.ui.ManageLaptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.ui.activity.MyPageActivity
import com.example.jub_jub_admin.ui.manageEq.ManageEquipmentViewModel
import com.example.jub_jub_admin.ui.manageEq.ManageItemList_PageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_manage_laptop.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*

class ManageLaptopActivity : AppCompatActivity() {

    private lateinit var pageView: ManageLaptopList_PageView
    private lateinit var viewModel: ManageLaptopViewModel

    private var lastSize = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_laptop)

        viewModel = ViewModelProvider(this).get(ManageLaptopViewModel::class.java)

        viewModel.init(applicationContext)

        pageView = ManageLaptopList_PageView(applicationContext, pageView_ManageLaptopActivity, viewModel)

        pageView.initViewPager()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            refresh()
            Log.d("TestLog_MLapAc","${viewModel.getShowList().size}")
        }

        ManageLaptopViewModel.i.observe(this, {
            viewModel.getDataFromServer()
        })

        ManageLaptopViewModel.list.observe(this, {
            pageView.syncPage()
        })
    }

    private fun refresh() {
        refreshLayout.isRefreshing = true
        viewModel.getDataFromServer()
        Log.d("TestLog", "새로고침 완료!")
        refreshLayout.isRefreshing = false
    }

    //타이틀 바 위젯들 온클릭 등록
    private fun setTitleBarItemsListener() {

        imageView_SearchMode_ManageLaptopActivity.setOnClickListener {
            setTitleBarSearchMode()
        }

        imageView_MyPage_ManageLaptopActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }

        imageView_BackArrow_ManageLaptopActivitySearchMode.setOnClickListener {
            editText_SearchText_ManageLaptopActivitySearchMode.setText("")
            setTitleBarViewMode()
        }

        setSearchFunction()
    }

    private fun setSearchFunction() =
        editText_SearchText_ManageLaptopActivitySearchMode.addTextChangedListener {

            viewModel.search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            var currentSize = viewModel.getShowList().size

            var beforeTime = System.currentTimeMillis()

            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }

            var afterTime = System.currentTimeMillis()

            Log.d("TestLog", "syncTime = ${(afterTime - beforeTime)}")
        }



    private fun setTitleBarSearchMode() {
        titleBar_ViewMode_ManageLaptopActivity.visibility = View.GONE
        titleBar_SearchMode_ManageLaptopActivity.visibility = View.VISIBLE
    }

    private fun setTitleBarViewMode() {
        titleBar_ViewMode_ManageLaptopActivity.visibility = View.VISIBLE
        titleBar_SearchMode_ManageLaptopActivity.visibility = View.GONE
        viewModel.search("")
        pageView.syncPage()
    }
}