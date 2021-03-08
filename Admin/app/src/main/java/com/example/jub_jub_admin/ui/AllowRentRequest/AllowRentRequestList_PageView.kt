package com.example.jub_jub_admin.ui.AllowRentRequest

import android.content.Context
import android.view.View
import com.example.jub_jub_admin.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class AllowRentRequestList_PageView(var context: Context, var view: View, var viewModel: AllowRentRequest_ViewModel) {

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = AllowRentRequest_ViewPagerAdapter(context, viewModel)

        setPageView = SetPageView(view, viewPager, viewModel.getShowList() as ArrayList<ArrayList<Any>>)

    }

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(viewModel.getShowList().size)
        setPageView.syncData(viewModel.getShowList() as ArrayList<ArrayList<Any>>)
    }




}