package com.example.jup_jup_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.RentStatusListManager
import kotlinx.android.synthetic.main.fragment_item_status_list.view.*

class MyRentList_ViewPagerAdapter(context: Context) : PagerAdapter() {


    private var context = context
    private lateinit var layoutInflater: LayoutInflater

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return RentStatusListManager.getShowList().size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.fragment_item_status_list, null)

        //나의 대여 목록.
        var adapter = MyRentList_RecyclerViewAdapter(RentStatusListManager.getShowList()[position])
        view.recyclerView_ItemStatusList.adapter = adapter
        adapter.notifyDataSetChanged()

        container.addView(view)

        return view
    }

}