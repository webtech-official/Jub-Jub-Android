package com.example.jub_jub_admin.ui.manageEquipment

import android.content.Context
import android.util.Log
import android.view.View
import com.example.jub_jub_admin.ui.util.SetPageView
import kotlinx.android.synthetic.main.layout_pageview.view.*

class ManageEquipment_PageView(var context: Context, var view: View, var viewModel: ManageEquipment_ViewModel){

    private lateinit var setPageView : SetPageView

    fun initViewPager() {

        val viewPager = view.viewPager

        viewPager.adapter = ManageEquipment_ViewPagerAdapter(context,viewModel)

        Log.d("TestLog_MaEqPV", "dataList = ${viewModel.getShowList()}")
        setPageView = SetPageView(view, viewPager, viewModel.getShowList() as ArrayList<ArrayList<Any>>)
    }

    fun syncPage(){
        //Log.d("TestLog", "ISList.size = ${ItemStatusListManager.getShowList().size}")
        setPageView.syncPage(viewModel.getShowList().size)
        setPageView.syncData(viewModel.getShowList() as ArrayList<ArrayList<Any>>)
    }

}

