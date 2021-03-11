package com.example.jub_jub_android.ui.view.myrentstatus

import android.content.Context
import android.view.View
import com.example.jub_jub_android.ui.adapter.viewpager.MyRentList_ViewPagerAdapter
import com.example.jub_jub_android.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class MyRentList_PageView(var context: Context, var view: View, var viewModel: MyRentList_ViewModel){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = MyRentList_ViewPagerAdapter (context, viewModel)

        setPageView = SetPageView(view, viewPager, viewModel.getDataList() as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        setPageView.syncPage(viewModel.getDataList().size)
        setPageView.syncData(viewModel.getDataList() as ArrayList<ArrayList<Any>>)
    }

}

