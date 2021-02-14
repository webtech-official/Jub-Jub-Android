package com.example.jub_jub_android.entity.singleton

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.ItemStatusDB
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.ui.activity.MainActivity
import java.io.ByteArrayOutputStream
import kotlin.collections.ArrayList


object ItemStatusListManager {
    private lateinit var itemStatusDB: ItemStatusDB

    private var dividedShowItemStatusList = ArrayList<ArrayList<Equipment>>()

    fun setDataList(context:Context, dataList: ArrayList<Equipment>){
        itemStatusDB = ItemStatusDB.getInstance(context)!!

        val thread = Thread(Runnable {
            itemStatusDB.itemStatusDAO().clear()


            Log.d("TestLog_ItemManager", "list Size ${dataList.size}")


            for(i in 0 until dataList.size){
                itemStatusDB.itemStatusDAO().insert(Equipment(dataList[i].category, dataList[i].count, dataList[i].image, dataList[i].name))
                Log.d("TestLog_ItemManager", "itemImage.length = ${dataList[i].image.length}")
                //Log.d("TestLog_ItemManager", "$i.id =  ${dataList[i].id}. $i.name = ${dataList[i].name}")
            }
            Log.d("TestLog_ItemManager", "데이터 입력 완료")

        })

        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }


        processShowList("")
        Log.d("TestLog_ItemManager", "데이터 가져오기 끝")

    }

    fun processShowList(key: String){
        var dataList = ArrayList<Equipment>()

        var r = Runnable {
            if(key == ""){
                dataList = itemStatusDB.itemStatusDAO().getAll() as ArrayList<Equipment>
            }
            else {
                dataList = itemStatusDB.itemStatusDAO().search("%$key%") as ArrayList<Equipment>
                //Log.d("TestLog", " $key = ${dataList.size}")
            }

            dividedShowItemStatusList.clear()
            var page = 0
            var cnt = 0
            dividedShowItemStatusList.add(ArrayList())

            for (i in 0 until dataList.size) {
                if (cnt == 5) {
                    dividedShowItemStatusList.add(ArrayList())
                    cnt = 0
                    page++
                }
                dividedShowItemStatusList[page].add(dataList[i])
                cnt++
            }

        }
        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

    }

    fun getShowList(): ArrayList<ArrayList<Equipment>>{
        return dividedShowItemStatusList
    }

}
