package com.example.jup_jup_android.ui.util

import android.content.Context
import android.util.Log
import android.view.View
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import com.example.jup_jup_android.ui.adapter.ItemStatusList_ViewPagerAdapter
import kotlinx.android.synthetic.main.layout_pageview.view.*

class ItemStatusList_PageView(var context: Context, var view: View, var  dataList: ArrayList<ArrayList<ItemStatus>>){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = ItemStatusList_ViewPagerAdapter(context)

        setPageView = SetPageView(view, viewPager, dataList as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(ItemStatusListManager.getShowList().size)
    }





}

