package com.example.jup_jup_android.entity.singleton

import com.example.jup_jup_android.ui.adapter.MyRentList_RecyclerViewAdapter
import com.example.jup_jup_android.ui.adapter.MyRentList_ViewPagerAdapter

object RentAdapter {

    lateinit var viewPagerAdpater : MyRentList_ViewPagerAdapter

    fun getViewPagerAdapter(): MyRentList_ViewPagerAdapter {
        return viewPagerAdpater
    }

    fun setViewPagerAdapter(adapter: MyRentList_ViewPagerAdapter){
        viewPagerAdpater = adapter
    }
}