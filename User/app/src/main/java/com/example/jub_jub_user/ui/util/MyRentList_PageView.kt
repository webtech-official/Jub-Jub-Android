package com.example.jub_jub_user.ui.util

import android.content.Context
import android.view.View
import com.example.jub_jub_user.entity.dataclass.RentStatus
import com.example.jub_jub_user.entity.singleton.RentStatusListManager
import com.example.jub_jub_user.ui.adapter.MyRentList_ViewPagerAdapter
import kotlinx.android.synthetic.main.layout_pageview.view.*

class MyRentList_PageView(var context: Context, var view: View, var dataList: ArrayList<ArrayList<RentStatus>>){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = MyRentList_ViewPagerAdapter (context)

        setPageView = SetPageView(view, viewPager, dataList as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        setPageView.syncPage(RentStatusListManager.getShowList().size)
    }

}

