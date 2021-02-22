package com.example.jub_jub_admin.entity.singleton

import android.content.Context
import com.example.jub_jub_admin.data.local.db.RentRequestDB
import com.example.jub_jub_admin.entity.dataclass.RentRequest
import com.example.jub_jub_admin.entity.dataclass.response.RentRequestDetailInfo
import com.example.jub_jub_admin.ui.util.MyUtil

object RentRequestListManager {
    private lateinit var rentRequestDB: RentRequestDB

    private var dividedShowList = ArrayList<ArrayList<RentRequestDetailInfo>>()

    private var dataList =  ArrayList<RentRequestDetailInfo>()

    fun setDataList(dataList: ArrayList<RentRequestDetailInfo>){
        this.dataList = dataList
        processShowList()
    }

    fun processShowList(){
        var r = Runnable {
            dividedShowList.clear()
            var page = 0
            var cnt = 0
            dividedShowList.add(ArrayList())

            for (i in 0 until dataList.size) {
                if(dataList[i].equipmentEnum == "ROLE_Waiting"){
                    if (cnt == 5) {
                        dividedShowList.add(ArrayList())
                        cnt = 0
                        page++
                    }
                    dividedShowList[page].add(dataList[i])
                    cnt++
                }
            }

        }
        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

    }

    fun getShowList(): ArrayList<ArrayList<RentRequestDetailInfo>> {
        return dividedShowList
    }

}