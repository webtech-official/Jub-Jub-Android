package com.example.jub_jub_admin.ui.ManageLaptop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.ui.ModifyLaptop.ModifyLaptop_Activity
import kotlinx.android.synthetic.main.layout_equipment_item.view.*
import java.util.ArrayList

class ManageLaptop_RecyclerViewAdapter(var dataList: ArrayList<LaptopStatus>): RecyclerView.Adapter<ManageLaptop_RecyclerViewAdapter.ViewHolder>() {

    //private var devideditemStatusList = dataList


    override fun getItemCount(): Int {
        Log.d("TestLog_ManageLaptopRecyclerViewAdapter", "${dataList.size}")
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
        fun bindItemStatusListItems(data: LaptopStatus){

            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.setOnClickListener {

                var intent = Intent(itemView.context, ModifyLaptop_Activity::class.java)

                intent.putExtra("Data", data)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                itemView.context.startActivity(intent)
            }
        }

        private fun setTextViewsText(data: LaptopStatus) {

            //Picasso.get().load(data.laptopSpec.image).into(itemView.imageView_ItemImage)
            itemView.textView_ItemName.text = data.laptopSpec.laptopName
            itemView.textView_ItemCategory.text = data.laptopSpec.laptopBrand
            itemView.textView_ItemCount.visibility = View.GONE
            //itemView.textView_ItemCount.text = "수량 : ${data.count}개"

        }

    }
}
