package com.example.jup_jup_android.entity.singleton

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList


object ItemStatusListManager {

    private var itemStatusList = ArrayList<ItemStatus>()

    private var originalDividedItemStatusList = ArrayList<ArrayList<ItemStatus>>()

    var dividedshowItemStatusList = ArrayList<ArrayList<ItemStatus>>()


    fun initItemStatusList(context: Context, cnt: Int){
//        /var itemStatusData = ItemStatus(BitmapFactory.decodeResource(context.resources, R.drawable.imageex), "DC모터", "모터", 5)

        var tempStatusList = ArrayList<ItemStatus>()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imageex)
        val byteStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        val baseString: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        for(i in 0..cnt/10){
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "DC모터", "모터", i))
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "블루투스 무선 마우스", "마우스", i))
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "28인치 모니터", "모니터", i))
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "치킨", "간식", i))
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "블루투스 무선 CK96 키보드", "키보드", i))
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "태블릿", "태블릿", i))
            tempStatusList.add(ItemStatus(i.toLong(), baseString, "김상현", "백엔드", i))
        }


        setItemStatusList(tempStatusList)
    }

    fun setItemStatusList(dataList: ArrayList<ItemStatus>){
        itemStatusList = dataList

        originalDividedItemStatusList.clear()
        for(i in 0..dataList.size/5){
            //Log.d("TestLog","i = $i")
            originalDividedItemStatusList.add(ArrayList())
            for(j in 0 until 5){
                //Log.d("TestLog","j = $j")
                if(i*5 + j >= dataList.size){
                    break
                }else{
                    originalDividedItemStatusList[i].add(dataList[i * 5 + j])

                }
            }
        }

        processShowList("")
    }

    @JvmName("getDividedItemStatusList1")
    fun getDividedItemStatusList(): ArrayList<ArrayList<ItemStatus>>{
        return originalDividedItemStatusList
    }



    fun processShowList(key: String){

        if(key == ""){
            dividedshowItemStatusList = originalDividedItemStatusList
        }else {
            dividedshowItemStatusList.clear()
            var page = 0
            var cnt = 0
            dividedshowItemStatusList.add(ArrayList())

            for (i in 0 until itemStatusList.size) {

                if (itemStatusList[i].name.toLowerCase(Locale.getDefault()).trim().contains(key) || itemStatusList[i].category.toLowerCase(Locale.getDefault()).contains(key))
                {
                    if (cnt == 5) {
                        dividedshowItemStatusList.add(ArrayList())
                        cnt = 0
                        page++
                    }
                    else {
                        dividedshowItemStatusList[page].add(itemStatusList[i])
                        cnt++
                    }
                }
            }
        }
    }


    fun getShowList(): ArrayList<ArrayList<ItemStatus>>{
        return dividedshowItemStatusList
    }

    fun setShowList(dataList : ArrayList<ArrayList<ItemStatus>>){
        dividedshowItemStatusList = dataList
    }

}
