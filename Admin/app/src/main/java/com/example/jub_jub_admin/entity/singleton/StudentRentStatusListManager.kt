package com.example.jub_jub_admin.entity.singleton

import android.content.Context
import android.util.Log
import com.example.jub_jub_admin.data.local.db.RentStatusDB
import com.example.jub_jub_admin.entity.dataclass.StudentRentStatus
import com.example.jub_jub_admin.ui.util.MyUtil

object StudentRentStatusListManager {

    private lateinit var rentStatusDB : RentStatusDB

    private var dividedShowList = ArrayList<ArrayList<StudentRentStatus>>()



    fun setDummyDataList(context: Context, cnt: Int){
        rentStatusDB = RentStatusDB.getInstance(context)!!
        var r = Runnable {

            val baseString = ""

            rentStatusDB.rentStatusDAO().clear()
            for(i in 0..cnt){
                when(i%3){
                    0 -> rentStatusDB.rentStatusDAO().insert(StudentRentStatus("1","DC모터", "모터", i, baseString,"반납"))
                    1 -> rentStatusDB.rentStatusDAO().insert(StudentRentStatus("1","DC모터", "모터", i, baseString,"대여"))
                    2 -> rentStatusDB.rentStatusDAO().insert(StudentRentStatus("1","DC모터", "모터", i, baseString,"연체"))
                }
            }
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

        processShowList("전체")
    }

    fun processShowList(key: String){
        var dataList = ArrayList<StudentRentStatus>()

        var r = Runnable {

            if(key == "전체"){
                dataList = rentStatusDB.rentStatusDAO().getAll() as ArrayList<StudentRentStatus>
                Log.d("TestLog", " rent withoutkey ${dataList.size}")
            }
            else {
                dataList = rentStatusDB.rentStatusDAO().search("%$key%") as ArrayList<StudentRentStatus>
                Log.d("TestLog", " rent $key = ${dataList.size}")
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

    fun getShowList(): ArrayList<ArrayList<StudentRentStatus>> {
        return dividedShowList
    }
}