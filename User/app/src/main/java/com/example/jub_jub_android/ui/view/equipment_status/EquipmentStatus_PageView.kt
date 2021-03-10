package com.example.jub_jub_android.ui.view.equipment_status

import android.content.Context
import android.util.Log
import android.view.View
import com.example.jub_jub_android.ui.adapter.viewpager.ItemStatusList_ViewPagerAdapter
import com.example.jub_jub_android.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class EquipmentStatus_PageView(var context: Context, var view: View, var viewModel: EquipmentStatus_ViewModel){

    private lateinit var setPageView : SetPageView

    init {
        val viewPager = view.viewPager

        viewPager.adapter = ItemStatusList_ViewPagerAdapter(context, viewModel)
        Log.d("TestLog_EqStPv", "dL = ${viewModel.getDataList()}")
        setPageView = SetPageView(view, viewPager, viewModel.getDataList() as ArrayList<ArrayList<Any>>)
    }

    /*
    fun initViewPager() {
        val viewPager = view.viewPager
        viewPager.adapter = ItemStatusList_ViewPagerAdapter(context, viewModel)
        Log.d("TestLog_EqStPv", "dL = ${viewModel.getDataList()}")
        setPageView = SetPageView(view, viewPager, viewModel.getDataList() as ArrayList<ArrayList<Any>>)
    } */

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(viewModel.getDataList().size)
        setPageView.syncData(viewModel.getDataList() as ArrayList<ArrayList<Any>>)
    }

}

