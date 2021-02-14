package com.example.jub_jub_admin.ui.util

import android.content.Context
import android.view.View
import com.example.jub_jub_admin.entity.dataclass.Equipment
import com.example.jub_jub_admin.entity.singleton.EquipmentManager
import com.example.jub_jub_admin.ui.adapter.ItemStatusList_ViewPagerAdapter
import kotlinx.android.synthetic.main.layout_pageview.view.*

class ItemStatusList_PageView(var context: Context, var view: View, var  dataList: ArrayList<ArrayList<Equipment>>){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = ItemStatusList_ViewPagerAdapter(context)

        setPageView = SetPageView(view, viewPager, dataList as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(EquipmentManager.getShowList().size)
    }





}

