package com.example.jub_jub_admin.ui.adapter.recyclerview

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.dataclass.Equipment
import com.example.jub_jub_admin.ui.activity.ModifyEquipmentActivity
import com.example.jub_jub_admin.ui.util.MyUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*
import java.util.ArrayList

class ManageEquipment_RecyclerViewAdpater(var dataList: ArrayList<Equipment>): RecyclerView.Adapter<ManageEquipment_RecyclerViewAdpater.ViewHolder>() {

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
        fun bindItemStatusListItems(data: Equipment){

            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.setOnClickListener {

                var intent = Intent(itemView.context, ModifyEquipmentActivity::class.java)

                intent.putExtra("Data", data)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)

                itemView.context.startActivity(intent)
            }
        }

        private fun setTextViewsText(data: Equipment) {
            Picasso.get().load(data.image).into(itemView.imageView_ItemImage)
            itemView.textView_ItemName.text = data.name
            itemView.textView_ItemCategory.text = data.category
            itemView.textView_ItemCount.text = "수량 : ${data.count}개"

        }

    }
}
