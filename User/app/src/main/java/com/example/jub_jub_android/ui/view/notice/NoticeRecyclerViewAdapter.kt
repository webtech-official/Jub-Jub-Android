package com.example.jub_jub_android.ui.view.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.ui.view.notice.detail.NoticeDetailActivity

class NoticeRecyclerViewAdapter(private val dataList: ArrayList<Int>): RecyclerView.Adapter<NoticeRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_notice_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(dataList[itemPosition])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: Int){
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, NoticeDetailActivity::class.java))
            }

        }
    }
}
