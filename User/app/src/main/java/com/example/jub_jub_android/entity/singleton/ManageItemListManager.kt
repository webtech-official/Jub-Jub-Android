package com.example.jub_jub_android.entity.singleton

import android.content.Context
import com.example.jub_jub_android.data.local.DB.ItemStatusDB
import com.example.jub_jub_android.entity.dataclass.ItemStatus
import com.example.jub_jub_android.ui.util.MyUtil
import kotlin.collections.ArrayList


object ManageItemListManager {
    private lateinit var itemStatusDB: ItemStatusDB

    private var dividedShowList = ArrayList<ArrayList<ItemStatus>>()

    fun setDummyData(context: Context){
        itemStatusDB = ItemStatusDB.getInstance(context)!!


        var r = Runnable {

            val baseString = MyUtil().getMotorTestImage(context)

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

        val thread = Thread(Runnable {

            if(key == ""){
                dataList = itemStatusDB.itemStatusDAO().getAll() as ArrayList<ItemStatus>
            }
            else {
                dataList = itemStatusDB.itemStatusDAO().search("%$key%") as ArrayList<ItemStatus>
            }
            MyUtil().processShowList(dataList as ArrayList<Any>, key, dividedShowList as ArrayList<ArrayList<Any>>)

        })

        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

    }

    fun getShowList(): ArrayList<ArrayList<ItemStatus>>{
        return dividedShowList
    }

}
