package com.example.jub_jub_android.ui.util.PageView

import android.content.Context
import android.view.View
import com.example.jub_jub_android.entity.dataclass.ItemStatus
import com.example.jub_jub_android.entity.dataclass.RentRequest
import com.example.jub_jub_android.entity.singleton.ManageItemListManager
import com.example.jub_jub_android.entity.singleton.RentRequestListManager
import com.example.jub_jub_android.ui.adapter.viewpager.AllowRentRequest_ViewPagerAdapter
import com.example.jub_jub_android.ui.adapter.viewpager.ManageItem_ViewPagerAdapter
import com.example.jub_jub_android.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class AllowRentRequestList_PageView(var context: Context, var view: View, var  dataList: ArrayList<ArrayList<RentRequest>>) {

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = AllowRentRequest_ViewPagerAdapter(context)

        setPageView = SetPageView(view, viewPager, dataList as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(RentRequestListManager.getShowList().size)
    }




}