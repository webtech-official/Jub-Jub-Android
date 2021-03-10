package com.example.jub_jub_android.ui.view.myrentstatus

import android.content.Context
import android.view.View
import com.example.jub_jub_android.entity.dataclass.MyEquipment
import com.example.jub_jub_android.entity.singleton.MyEquipmentListManager
import com.example.jub_jub_android.ui.adapter.viewpager.MyRentList_ViewPagerAdapter
import com.example.jub_jub_android.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class MyRentList_PageView(var context: Context, var view: View, var dataList: ArrayList<ArrayList<MyEquipment>>){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = MyRentList_ViewPagerAdapter (context)

        setPageView = SetPageView(view, viewPager, dataList as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        setPageView.syncPage(MyEquipmentListManager.getShowList().size)
    }

}

