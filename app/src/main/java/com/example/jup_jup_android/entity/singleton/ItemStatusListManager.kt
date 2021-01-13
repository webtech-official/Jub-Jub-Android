package com.example.jup_jup_android.entity.singleton

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus

object ItemStatusListManager {

    private var itemStatusList = ArrayList<ItemStatus>()

    private var devidedItemStatusList = ArrayList<ArrayList<ItemStatus>>()



    fun initItemStatusList(context: Context, cnt: Int){
//        /var itemStatusData = ItemStatus(BitmapFactory.decodeResource(context.resources, R.drawable.imageex), "DC모터", "모터", 5)

        var tempStatusList = ArrayList<ItemStatus>()
        for(i in 0..cnt){
            tempStatusList.add(ItemStatus(BitmapFactory.decodeResource(context.resources, R.drawable.imageex), "DC모터", "모터", i))
        }


        setItemStatusList(tempStatusList)
    }

    @JvmName("getItemStatusList1")
    fun getItemStatusList(): ArrayList<ItemStatus>{
        return itemStatusList
    }

    fun setItemStatusList(dataList: ArrayList<ItemStatus>){
        itemStatusList = dataList

        var tempList = ArrayList<ItemStatus>()

        var cnt = 0

        for(i in 0..dataList.size/5){
            //Log.d("TestLog","i = $i")
            devidedItemStatusList.add(ArrayList())
            for(j in 0 until 5){
                //Log.d("TestLog","j = $j")
                if(i*5 + j >= dataList.size){
                    break
                }else{
                    devidedItemStatusList[i].add(dataList[i*5 + j])
                    //Log.d("TestLog","[i][j] = ${devidedItemStatusList[i][j]}")
                }
            }
        }

        //Log.d("TestLog","complete = ${devidedItemStatusList}")
    }

    @JvmName("getDevidedItemStatusList1")
    fun getDevidedItemStatusList(): ArrayList<ArrayList<ItemStatus>>{
        return devidedItemStatusList
    }
}
