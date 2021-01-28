package com.example.jub_jub_android.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.entity.dataclass.RentStatus
import com.example.jub_jub_android.ui.util.MyUtil
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*

class MyRentList_RecyclerViewAdapter(var dataList: ArrayList<RentStatus>):RecyclerView.Adapter<MyRentList_RecyclerViewAdapter.ViewHolder>() {


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

        fun bindRentItemListItems(data: RentStatus){

            setTextViewsText(data)

        }

        private fun setTextViewsText(data: RentStatus) {
            itemView.imageView_ItemImage.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
            itemView.textView_ItemName.text = data.name
            itemView.textView_ItemCategory.text = data.category
            itemView.textView_ItemCount.text = "수량 : ${data.count}개"
            itemView.textView_RentStatus_Item.visibility = View.VISIBLE
            itemView.textView_RentStatus_Item.text = data.status

            when(data.status){
                "반납" -> setRentStatus(itemView, R.drawable.round_black_back_white_edge, R.color.white)
                "대여" -> setRentStatus(itemView, R.drawable.round_yellow_back_yellow_edge, R.color.black)
                "연체" -> setRentStatus(itemView, R.drawable.round_white_back_white_edge, R.color.black)
                else -> itemView.textView_RentStatus_Item.text = "?"

            }
        }

        private fun setRentStatus(view: View, backgroundId: Int, textColorId: Int){
            itemView.textView_RentStatus_Item.background = ContextCompat.getDrawable(view.context, backgroundId)
            itemView.textView_RentStatus_Item.setTextColor(ContextCompat.getColor(view.context, textColorId))
        }
    }

}
