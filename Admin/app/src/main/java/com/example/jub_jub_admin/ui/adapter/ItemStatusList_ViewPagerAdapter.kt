package com.example.jub_jub_admin.ui.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.singleton.ItemStatusListManager
import com.example.jub_jub_admin.ui.adapter.ItemStatusList_RecyclerViewAdpater
import kotlinx.android.synthetic.main.fragment_item_status_list.view.*

class ItemStatusList_ViewPagerAdapter(var context: Context) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater


    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return ItemStatusListManager.getShowList().size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.fragment_item_status_list, null)

        //메인 화면 (기자재 목록)
        var adapter = ItemStatusList_RecyclerViewAdpater(ItemStatusListManager.getShowList()[position])
        view.recyclerView_ItemStatusList.adapter = adapter
        adapter.notifyDataSetChanged()

        container.addView(view)

        return view
    }

}