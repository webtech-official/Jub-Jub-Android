package com.example.jub_jub_admin.ui.ManageLaptop

import android.content.Context
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.ui.manageEq.ManageEquipmentViewModel
import kotlinx.android.synthetic.main.fragment_item_status_list.view.*

class ManageLaptop_ViewpagerAdapter(val context: Context, private val viewModel: ManageLaptopViewModel) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater


    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        Log.d("TestLog_ManageLaptopViewPagerAdapter", "${viewModel.getShowList().size}")
        return viewModel.getShowList().size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.fragment_item_status_list, null)

        //메인 화면 (기자재 목록)
        var adapter = ManageLaptop_RecyclerViewAdapter(viewModel.getShowList()[position])
        view.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        container.addView(view)

        return view
    }

}