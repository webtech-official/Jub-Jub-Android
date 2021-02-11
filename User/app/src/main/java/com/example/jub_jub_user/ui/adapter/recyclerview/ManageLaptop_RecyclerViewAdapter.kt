package com.example.jub_jub_user.ui.adapter.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_user.R
import com.example.jub_jub_user.entity.dataclass.LaptopStatus
import com.example.jub_jub_user.ui.activity.ModifyLaptopActivity
import com.example.jub_jub_user.ui.util.MyUtil
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*
import java.util.ArrayList

class ManageLaptop_RecyclerViewAdapter(var dataList: ArrayList<LaptopStatus>): RecyclerView.Adapter<ManageLaptop_RecyclerViewAdapter.ViewHolder>() {

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
        fun bindItemStatusListItems(data: LaptopStatus){

            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.setOnClickListener {

                var intent = Intent(itemView.context, ModifyLaptopActivity::class.java)

                intent.putExtra("Data", data)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                itemView.context.startActivity(intent)
            }
        }

        private fun setTextViewsText(data: LaptopStatus) {
            itemView.imageView_ItemImage.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
            itemView.textView_ItemName.text = data.name
            itemView.textView_ItemCategory.text = data.category
            itemView.textView_ItemCount.text = "수량 : ${data.count}개"

        }

    }
}
