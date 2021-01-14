package com.example.jup_jup_android.ui.adapter

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import com.example.jup_jup_android.entity.dataclass.RentStatus
import com.example.jup_jup_android.ui.activity.RentActivity
import com.example.jup_jup_android.ui.util.MyUtil
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*

class ItemStatusList_RecyclerViewAdpater (dataList: ArrayList<ItemStatus>):RecyclerView.Adapter<ItemStatusList_RecyclerViewAdpater.ViewHolder>() {

    private var devideditemStatusList = dataList

    override fun getItemCount(): Int {
        return devideditemStatusList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipmentlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItems(devideditemStatusList[itemPosition])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data: ItemStatus){

            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.setOnClickListener {

                var intent = Intent(itemView.context, RentActivity::class.java)

                intent.putExtra("Data", data)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)

                itemView.context.startActivity(intent)
            }
        }

        fun bindItems(data: RentStatus){
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

        private fun setTextViewsText(data: RentStatus) {
            itemView.imageView_ItemImage_Item.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
            itemView.textView_ItemName_Item.text = data.name
            itemView.textView_ItemCategory_Item.text = data.category
            itemView.textView_ItemCount_Item.text = "수량 : ${data.count}개"

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
