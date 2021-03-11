package com.example.jub_jub_android.ui.adapter.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.jub_jub_android.R
import com.example.jub_jub_android.ui.adapter.recyclerview.MyRentList_RecyclerViewAdapter
import com.example.jub_jub_android.ui.view.myrentstatus.MyRentList_ViewModel
import kotlinx.android.synthetic.main.fragment_item_status_list.view.*

class MyRentList_ViewPagerAdapter(val context: Context, val viewModel: MyRentList_ViewModel) : PagerAdapter() {


    private lateinit var layoutInflater: LayoutInflater

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return viewModel.getDataList().size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.fragment_item_status_list, null)

        //나의 대여 목록.
        var adapter = MyRentList_RecyclerViewAdapter(viewModel.getDataList()[position])
        view.recyclerView_ItemStatusList.adapter = adapter
        adapter.notifyDataSetChanged()

        container.addView(view)

        return view
    }

}