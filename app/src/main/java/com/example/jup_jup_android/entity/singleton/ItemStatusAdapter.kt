package com.example.jup_jup_android.entity.singleton

import com.example.jup_jup_android.ui.adapter.ItemStatusList_ViewPagerAdapter

object ItemStatusAdapter {

    lateinit var viewPagerAdpater : ItemStatusList_ViewPagerAdapter

    fun getViewPagerAdapter(): ItemStatusList_ViewPagerAdapter {
        return viewPagerAdpater
    }

    fun setViewPagerAdapter(adapter: ItemStatusList_ViewPagerAdapter){
        viewPagerAdpater = adapter
    }

}