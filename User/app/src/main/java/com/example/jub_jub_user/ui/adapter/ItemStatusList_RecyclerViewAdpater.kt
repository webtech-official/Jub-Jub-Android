package com.example.jub_jub_user.ui.adapter

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_user.entity.dataclass.ItemStatus
import com.example.jub_jub_user.ui.activity.RentActivity
import com.example.jub_jub_user.ui.util.MyUtil
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*
import java.util.ArrayList

class ItemStatusList_RecyclerViewAdpater(var dataList: ArrayList<ItemStatus>): RecyclerView.Adapter<ItemStatusList_RecyclerViewAdpater.ViewHolder>() {

    //private var devideditemStatusList = dataList


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipmentlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(dataList[itemPosition])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: ItemStatus){

            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.setOnClickListener {

                var intent = Intent(itemView.context, RentActivity::class.java)

                intent.putExtra("Data", data)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)

                itemView.context.startActivity(intent)
            }
        }

        private fun setTextViewsText(data: ItemStatus) {
            itemView.imageView_ItemImage_Item.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
            itemView.textView_ItemName_Item.text = data.name
            itemView.textView_ItemCategory_Item.text = data.category
            itemView.textView_ItemCount_Item.text = "수량 : ${data.count}개"

        }

    }
}
