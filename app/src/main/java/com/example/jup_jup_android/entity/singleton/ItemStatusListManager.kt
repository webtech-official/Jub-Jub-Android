package com.example.jup_jup_android.entity.singleton

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import com.example.jup_jup_android.entity.dataclass.RentStatus
import java.io.ByteArrayOutputStream


object ItemStatusListManager {

    private var itemStatusList = ArrayList<ItemStatus>()

    private var originalDevidedItemStatusList = ArrayList<ArrayList<ItemStatus>>()

    var devidedshowItemStatusList = ArrayList<ArrayList<ItemStatus>>()


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

        var tempList = ArrayList<ItemStatus>()

        var cnt = 0
        originalDevidedItemStatusList.clear()
        for(i in 0..dataList.size/5){
            //Log.d("TestLog","i = $i")
            originalDevidedItemStatusList.add(ArrayList())
            for(j in 0 until 5){
                //Log.d("TestLog","j = $j")
                if(i*5 + j >= dataList.size){
                    break
                }else{
                    originalDevidedItemStatusList[i].add(dataList[i * 5 + j])
                    //Log.d("TestLog","[i][j] = ${devidedItemStatusList[i][j]}")
                }
            }
        }

        processShowList("")
    }

    @JvmName("getDevidedItemStatusList1")
    fun getDevidedItemStatusList(): ArrayList<ArrayList<ItemStatus>>{
        return originalDevidedItemStatusList
    }



    fun processShowList(key: String){

        if(key == ""){
            devidedshowItemStatusList = originalDevidedItemStatusList
        }else {

            devidedshowItemStatusList.clear()
            Log.d("BeforeLowerCase", "$key")
            key.toLowerCase()
            Log.d("AfterLowerCase", "$key")
            var page = 0
            var cnt = 0
            devidedshowItemStatusList.add(ArrayList())

            for (i in 0 until itemStatusList.size) {

                if (itemStatusList[i].name.toLowerCase().contains(key) || itemStatusList[i].category.toLowerCase().contains(key)) {
                    if (cnt == 5) {
                        devidedshowItemStatusList.add(ArrayList())
                        cnt = 0
                        page++
                    } else {
                        devidedshowItemStatusList[page].add(itemStatusList[i])
                        cnt++
                    }
                }
            }
        }
    }


    fun getShowList(): ArrayList<ArrayList<ItemStatus>>{
        return devidedshowItemStatusList
    }

    fun setShowList(dataList : ArrayList<ArrayList<ItemStatus>>){
        devidedshowItemStatusList = dataList
    }

}
