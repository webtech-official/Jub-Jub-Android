package com.example.jup_jup_android.ui.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import com.example.jup_jup_android.ui.activity.RentActivity
import kotlinx.android.synthetic.main.layout_equipmentlist_item.view.*

class ItemStatusList_RecyclerViewAdpater (val context: Context, pPosition: Int):RecyclerView.Adapter<ItemStatusList_RecyclerViewAdpater.ViewHolder>() {
    var pagePosition = pPosition
    private var devideditemStatusList = ItemStatusListManager.getDevidedItemStatusList()[pagePosition]

    fun getDataList(position: Int): ArrayList<ItemStatus> {

        var dataList = ItemStatusListManager.getItemStatusList()
        Log.d("TestLog","${dataList}")
        if(position == dataList.size/5){
            Log.d("TestLog","${dataList.subList(position*5, dataList.size)}")
            return dataList.subList(position*5, dataList.size) as ArrayList<ItemStatus>

        }else{
            Log.d("TestLog","${dataList.subList(position*5, position*5 + 4)}")
            return dataList.subList(position*5, position*5 + 4) as ArrayList<ItemStatus>
        }

    }

    //var position : Int = 0
    //아이템의 갯수
    override fun getItemCount(): Int {
        return devideditemStatusList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStatusList_RecyclerViewAdpater.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipmentlist_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ItemStatusList_RecyclerViewAdpater.ViewHolder, itemPosition: Int) {
        holder.bindItems(devideditemStatusList[itemPosition], pagePosition, itemPosition)
        //this.position = position
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data: ItemStatus, pagePosition: Int, itemPosition: Int){
            //이미지표시

//            Glide.with(itemView.context).load(data.imageResource)
//                .into(itemView.recyclerView_ImageView)
            itemView.imageView_ItemImage_EquipmentList_Item.setImageBitmap(data.itemImage)
            itemView.textView_ItemName_EquipmentList_Item.text = data.itemName
            itemView.textView_ItemCategory_EquipmentList_Item.text = data.itemCategory
            itemView.textView_ItemCount_EquipmentList_Item.text = "수량 : ${data.itemCount}개"


            //각각의 아이템 클릭시

            itemView.setOnClickListener {
                //여기서 토스터를 어떻게?
                //Toast.makeText(itemView.context, "아이템 '${data.name}'를 클릭했습니다.", Toast.LENGTH_LONG).show()
                makeToastitemView(itemView)
//                val intent = Intent(itemView.context,::class.java)
//                intent.putExtra("field", data.name)
//                itemView.context.startActivity(intent)
                var intent = Intent(itemView.context, RentActivity::class.java)
                intent.putExtra("PageIndex", pagePosition)
                intent.putExtra("ItemIndex", itemPosition)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)
            }


        }

        private fun makeToastitemView(itemView: View){
            Log.d("TestLog", "position = $position 를 클릭했습니다.")
            Log.d("TestLog", "adapterPosition = $adapterPosition 를 클릭했습니다.")
            Log.d("TestLog", "layoutPosition = $layoutPosition 를 클릭했습니다.")
            Log.d("TestLog", "oldPosition = $oldPosition 를 클릭했습니다.")


        }


    }



}
