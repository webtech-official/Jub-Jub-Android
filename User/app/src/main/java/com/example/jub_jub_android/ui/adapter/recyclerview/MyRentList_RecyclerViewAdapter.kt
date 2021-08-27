package com.example.jub_jub_android.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.entity.dataclass.MyEquipment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*

class MyRentList_RecyclerViewAdapter(var dataList: ArrayList<MyEquipment>):RecyclerView.Adapter<MyRentList_RecyclerViewAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipmentlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindRentItemListItems(dataList[itemPosition])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindRentItemListItems(data: MyEquipment){

            setTextViewsText(data)

        }

        private fun setTextViewsText(data: MyEquipment) {
            Picasso.get().load(data.image).into(itemView.imageView_ItemImage_Item)
            itemView.textView_ItemName_Item.text = data.name
            itemView.textView_ItemCategory_Item.text = data.category
            itemView.textView_ItemCount_Item.text = "수량 : ${data.count}개"
            itemView.textView_RentStatus_Item5123123.visibility = View.VISIBLE
            itemView.textView_RentStatus_Item5123123.text = data.status

            when(data.status){
                "대기" -> setRentStatus(itemView, R.drawable.bg_waiting, R.color.black)
                "반납" -> setRentStatus(itemView, R.drawable.bg_return, R.color.white)
                "대여" -> setRentStatus(itemView, R.drawable.bg_white_border_10dp, R.color.black)
                "승인" -> setRentStatus(itemView, R.drawable.bg_overdue, R.color.black)
                "거절" -> setRentStatus(itemView, R.drawable.bg_reject, R.color.black)
                else -> itemView.textView_RentStatus_Item5123123.text = "?"

            }
        }

        private fun setRentStatus(view: View, backgroundId: Int, textColorId: Int){
            itemView.textView_RentStatus_Item5123123.background = ContextCompat.getDrawable(view.context, backgroundId)
            itemView.textView_RentStatus_Item5123123.setTextColor(ContextCompat.getColor(view.context, textColorId))
        }
    }

}
