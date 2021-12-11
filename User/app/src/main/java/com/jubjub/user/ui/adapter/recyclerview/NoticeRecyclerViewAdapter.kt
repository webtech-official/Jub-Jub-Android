package com.jubjub.user.ui.adapter.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jubjub.user.R
import com.jubjub.user.databinding.LayoutNoticeItemBinding
import com.jubjub.user.entity.dataclass.response.Notice
import com.jubjub.user.ui.view.notice.detail.NoticeDetailActivity

class NoticeRecyclerViewAdapter(private val dataList: ArrayList<Notice>): RecyclerView.Adapter<NoticeRecyclerViewAdapter.ViewHolder>() {

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

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: Notice){

            DataBindingUtil.bind<LayoutNoticeItemBinding>(view)!!.notice = data

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, NoticeDetailActivity::class.java))
            }

        }
    }
}
