package com.example.jub_jub_admin.entity.singleton

import android.content.Context
import com.example.jub_jub_admin.data.local.db.RentRecordDB
import com.example.jub_jub_admin.entity.dataclass.RentRecord
import com.example.jub_jub_admin.ui.util.MyUtil

object RentRecordListManager {
    private lateinit var rentRecordDB: RentRecordDB

    private var dividedShowList = ArrayList<ArrayList<RentRecord>>()

    fun setDummyData(context: Context){
        rentRecordDB = RentRecordDB.getInstance(context)!!


        var r = Runnable {

            //val baseString = MyUtil.getMotorTestImage(context)

            rentRecordDB.rentRecordDAO().clear()
            var cnt = 1
            for(i in 0..10){


//                rentRecordDB.rentRecordDAO().insert(RentRecord(++cnt, baseString, "DC모터", "모터", i))
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
        var dataList = ArrayList<RentRecord>()


        var r = Runnable {
            if(key == ""){
                dataList = rentRecordDB.rentRecordDAO().getAll() as ArrayList<RentRecord>
                //Log.d("TestLog", " withoutkey ${dataList.size}")
                //Log.d("TestLog", "Key = \"\" , getoriginalDList = ${getOriginalDividedRentRecordList().size} ")
                //Log.d("TestLog", "Key = \"\" , originalDList = ${originalDividedRentRecordList.size} ")
            }
            else {
                dataList = rentRecordDB.rentRecordDAO().search("%$key%") as ArrayList<RentRecord>
                //Log.d("TestLog", " $key = ${dataList.size}")
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

    fun getShowList(): ArrayList<ArrayList<RentRecord>>{
        return dividedShowList
    }
}