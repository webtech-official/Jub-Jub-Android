package com.example.jub_jub_android.ui.view.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.ui.view.rent_request.RentActivity
import com.example.jub_jub_android.ui.view.rent_request.TmpRentActivity

class EquipmentRecyclerViewAdapter(val dataList: ArrayList<Int>): RecyclerView.Adapter<EquipmentRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipment_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(dataList[itemPosition])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: Int){

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, TmpRentActivity::class.java))
            }

        }
    }
}
