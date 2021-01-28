package com.example.jub_jub_user.entity.singleton

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.jub_jub_android.R
import com.example.jub_jub_user.data.local.ItemStatusDB
import com.example.jub_jub_user.entity.dataclass.ItemStatus
import java.io.ByteArrayOutputStream
import kotlin.collections.ArrayList


object ItemStatusListManager {
    private lateinit var itemStatusDB: ItemStatusDB

    private var dividedShowItemStatusList = ArrayList<ArrayList<ItemStatus>>()

    fun setDummyData(context: Context){
        itemStatusDB = ItemStatusDB.getInstance(context)!!


        var r = Runnable {
            val byteStream = ByteArrayOutputStream()
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imageex)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
            val byteArray: ByteArray = byteStream.toByteArray()
            val baseString = Base64.encodeToString(byteArray, Base64.DEFAULT)

            itemStatusDB.itemStatusDAO().clear()
            var cnt = 1
            for(i in 0..10){

                itemStatusDB.itemStatusDAO().insert(ItemStatus(++cnt, baseString, "DC모터", "모터", i))
                itemStatusDB.itemStatusDAO().insert(ItemStatus(++cnt, baseString, "28인치 모니터", "모니터", i))
                itemStatusDB.itemStatusDAO().insert(ItemStatus(++cnt, baseString, "치킨", "간식", i))
                itemStatusDB.itemStatusDAO().insert(ItemStatus(++cnt, baseString, "블루투스 무선 CK96 키보드", "키보드", i))
                itemStatusDB.itemStatusDAO().insert(ItemStatus(++cnt, baseString, "김상현", "백엔드", i))
                itemStatusDB.itemStatusDAO().insert(ItemStatus(++cnt, baseString, "태블릿", "태블릿", i))
            }
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

        processShowList("")

    }

    fun processShowList(key: String){
        var dataList = ArrayList<ItemStatus>()


        var r = Runnable {
            if(key == ""){
                dataList = itemStatusDB.itemStatusDAO().getAll() as ArrayList<ItemStatus>
                //Log.d("TestLog", " withoutkey ${dataList.size}")
                //Log.d("TestLog", "Key = \"\" , getoriginalDList = ${getOriginalDividedItemStatusList().size} ")
                //Log.d("TestLog", "Key = \"\" , originalDList = ${originalDividedItemStatusList.size} ")
            }
            else {
                dataList = itemStatusDB.itemStatusDAO().search("%$key%") as ArrayList<ItemStatus>
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

    fun getShowList(): ArrayList<ArrayList<ItemStatus>>{
        return dividedShowItemStatusList
    }

}
