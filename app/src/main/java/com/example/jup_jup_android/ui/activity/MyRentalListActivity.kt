package com.example.jup_jup_android.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import com.example.jup_jup_android.ui.adapter.ViewPagerAdapter
import com.example.jup_jup_android.ui.util.SetPageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_rental_list.*
import kotlinx.android.synthetic.main.layout_pageview.view.*

class MyRentalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_rental_list)

        SetPageView(applicationContext, pageView_MyRentalListActivity, ItemStatusListManager.getDevidedItemStatusList()).initViewPager()

        setTitleBarItemsOnclick()
    }


    private fun setTitleBarItemsOnclick() {

        imageView_BackArrow_MyRentalActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }
    }


}