package com.example.jub_jub_android.ui.view.main.myrent

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.ui.view.main.EquipmentRecyclerViewAdapter
import com.example.jub_jub_android.ui.view.rent_request.TmpRentActivity

class RentListRecyclerViewAdapter(val dataList: ArrayList<Int>): RecyclerView.Adapter<RentListRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = when(viewType){
            0 -> inflate(parent, R.layout.layout_rent_item_return)
            1 -> inflate(parent, R.layout.layout_rent_item_rental)
            2 -> inflate(parent, R.layout.layout_rent_item_waiting)
            3 -> inflate(parent, R.layout.layout_rent_item_reject)
            4 -> inflate(parent, R.layout.layout_rent_item_overdue)
            else -> inflate(parent, R.layout.layout_rent_item_return)
        }

        return ViewHolder(v)
    }

    private fun inflate(viewGroup: ViewGroup, resId: Int ): View {
        return LayoutInflater.from(viewGroup.context).inflate(resId, viewGroup, false)
    }

    override fun getItemViewType(position: Int): Int {
        return position
        //super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(dataList[itemPosition])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: Int){

            itemView.setOnClickListener {
            }

        }
    }
}
