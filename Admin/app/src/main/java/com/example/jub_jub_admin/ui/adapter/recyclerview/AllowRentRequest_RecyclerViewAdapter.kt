package com.example.jub_jub_admin.ui.adapter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.dataclass.RentRequest
import com.example.jub_jub_admin.ui.util.MyUtil
import kotlinx.android.synthetic.main.layout_alertdialog.*
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*
import java.util.ArrayList

class AllowRentRequest_RecyclerViewAdapter(val context: Context, var dataList: ArrayList<RentRequest>): RecyclerView.Adapter<AllowRentRequest_RecyclerViewAdapter.ViewHolder>() {

    //private var devideditemStatusList = dataList


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipmentlist_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(context, dataList[itemPosition], holder)
    }


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val myUtil = MyUtil()


        fun bindItemStatusListItems(context: Context, data: RentRequest, holder: ViewHolder){
            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.button_AllowRentRequest.setOnClickListener {
                allowRequest(context, it.context)
            }

            itemView.button_DenyRentRequest.setOnClickListener {
                Toast.makeText(context, "con 거절" , Toast.LENGTH_SHORT).show()
                denyRequest(context, itemView.context)
                Toast.makeText(context, "con 거절" , Toast.LENGTH_SHORT).show()
            }
        }

        private fun allowRequest(dialogContext: Context, itemContext: Context) {
            val dialog = myUtil.makeBaseDialog(dialogContext, "수락")

            dialog.accept.setOnClickListener {
                Toast.makeText(itemContext, "item 대여 신청 수락" , Toast.LENGTH_SHORT).show()
                Toast.makeText(dialogContext, "dialog 대여 신청 수락" , Toast.LENGTH_SHORT).show()
                Toast.makeText(dialog.context, "dialog 대여 신청 수락" , Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                Toast.makeText(itemContext, "item 대여 신청 수락" , Toast.LENGTH_SHORT).show()
                Toast.makeText(dialogContext, "dialog 대여 신청 수락" , Toast.LENGTH_SHORT).show()

            }

            dialog.cancel.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun denyRequest(dialogContext: Context, itemContext: Context) {
            val dialog = myUtil.makeBaseDialog(dialogContext, "거절")

            dialog.accept.setOnClickListener {
                Toast.makeText(itemContext, "item 대여 신청 거절" , Toast.LENGTH_SHORT).show()
                Toast.makeText(dialogContext, "dialog 대여 신청 거절" , Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                Toast.makeText(itemContext, "item 대여 신청 거절" , Toast.LENGTH_SHORT).show()
                Toast.makeText(dialogContext, "dialog 대여 신청 거절" , Toast.LENGTH_SHORT).show()
            }

            dialog.cancel.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun setTextViewsText(data: RentRequest) {
            itemView.textView_StudentNumber.visibility = View.VISIBLE
            itemView.button_AllowRentRequest.visibility = View.VISIBLE
            itemView.button_DenyRentRequest.visibility = View.VISIBLE

            itemView.imageView_ItemImage.setImageBitmap(myUtil.convertBase64ToBitmap(data.image))
            itemView.textView_ItemName.text = data.itemName
            itemView.textView_ItemCategory.text = data.category
            itemView.textView_ItemCount.text = "수량 : ${data.count}개"

            itemView.textView_StudentNumber.text = data.studentNumber.toString()

        }

    }
}
