package com.example.jub_jub_admin.ui.util.PageView

import android.content.Context
import android.view.View
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.entity.singleton.ManageLaptopListManager
import com.example.jub_jub_admin.ui.adapter.viewpager.ManageLaptop_ViewpagerAdapter
import com.example.jub_jub_admin.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class ManageLaptopList_PageView(var context: Context, var view: View, var  dataList: ArrayList<ArrayList<LaptopStatus>>){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = ManageLaptop_ViewpagerAdapter(context)

        setPageView = SetPageView(view, viewPager, dataList as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(ManageLaptopListManager.getShowList().size)
    }





}