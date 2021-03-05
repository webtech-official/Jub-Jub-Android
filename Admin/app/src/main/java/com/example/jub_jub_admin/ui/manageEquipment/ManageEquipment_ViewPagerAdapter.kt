package com.example.jub_jub_admin.ui.manageEquipment

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jub_jub_admin.R
import kotlinx.android.synthetic.main.fragment_item_status_list.view.*

class ManageEquipment_ViewPagerAdapter(val context: Context, private val viewModel: ManageEquipmentViewModel) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater


    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return viewModel.getShowList().size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.fragment_item_status_list, null)

        //메인 화면 (기자재 목록)
        var adapter = ManageEquipment_RecyclerViewAdpater(viewModel.getShowList()[position])
        view.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        container.addView(view)

        return view
    }

}