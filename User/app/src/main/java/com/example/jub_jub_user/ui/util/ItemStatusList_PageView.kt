package com.example.jub_jub_user.ui.util

import android.content.Context
import android.view.View
import com.example.jub_jub_user.entity.dataclass.ItemStatus
import com.example.jub_jub_user.entity.singleton.ItemStatusListManager
import com.example.jub_jub_user.ui.adapter.ItemStatusList_ViewPagerAdapter
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

