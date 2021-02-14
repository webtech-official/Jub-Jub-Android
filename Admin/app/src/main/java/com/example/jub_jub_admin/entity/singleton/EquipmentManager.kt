package com.example.jub_jub_admin.entity.singleton

import android.content.Context
import android.util.Log
import com.example.jub_jub_admin.data.local.db.EquipmentDB
import com.example.jub_jub_admin.entity.dataclass.Equipment
import kotlin.collections.ArrayList


object EquipmentManager {
    private lateinit var equipmentDB: EquipmentDB

    private var dividedShowList = ArrayList<ArrayList<Equipment>>()


    fun setDataList(context:Context, dataList: ArrayList<Equipment>){
        equipmentDB = EquipmentDB.getInstance(context)!!

        val thread = Thread(Runnable {
            equipmentDB.equipmentDAO().clear()


            Log.d("TestLog_ItemManager", "list Size ${dataList.size}")

            for(i in 0 until dataList.size){
                equipmentDB.equipmentDAO().insert(dataList[i])
                Log.d("TestLog_EquipmentManager", "dL.size = ${dataList.size}")

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
                dataList = equipmentDB.equipmentDAO().getAll() as ArrayList<Equipment>
                Log.d("TestLog_EquipmentManager", "dL.size = ${dataList.size}")
            }
            else {
                dataList = equipmentDB.equipmentDAO().search("%$key%") as ArrayList<Equipment>
            }

            dividedShowList.clear()
            var page = 0
            var cnt = 0
            dividedShowList.add(ArrayList())

            for (i in 0 until dataList.size) {
                if (cnt == 5) {
                    dividedShowList.add(ArrayList())
                    cnt = 0
                    page++
                }
                dividedShowList[page].add(dataList[i])
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
        return dividedShowList
    }

}