package com.example.jub_jub_admin.entity.singleton

import android.content.Context
import com.example.jub_jub_admin.data.local.db.RentRequestDB
import com.example.jub_jub_admin.entity.dataclass.RentRequest
import com.example.jub_jub_admin.ui.util.MyUtil

object RentRequestListManager {
    private lateinit var rentRequestDB: RentRequestDB

    private var dividedShowList = ArrayList<ArrayList<RentRequest>>()

    fun setDummyData(context: Context){
        rentRequestDB = RentRequestDB.getInstance(context)!!


        var r = Runnable {

            val baseString = MyUtil.getMotorTestImage(context)

            rentRequestDB.rentRequestDAO().clear()
            var cnt = 1
            for(i in 0..10){
                rentRequestDB.rentRequestDAO().insert(RentRequest("DC모터", baseString, "모터" , 2205, cnt++))
                rentRequestDB.rentRequestDAO().insert(RentRequest("블루투스 무선 CK96 키보드", baseString, "키보드" , 2204, cnt++))

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
        var dataList = ArrayList<RentRequest>()


        var r = Runnable {
            if(key == ""){
                dataList = rentRequestDB.rentRequestDAO().getAll() as ArrayList<RentRequest>
                //Log.d("TestLog", " withoutkey ${dataList.size}")
                //Log.d("TestLog", "Key = \"\" , getoriginalDList = ${getOriginalDividedRentRequestList().size} ")
                //Log.d("TestLog", "Key = \"\" , originalDList = ${originalDividedRentRequestList.size} ")
            }
            else {
                dataList = rentRequestDB.rentRequestDAO().search("%$key%") as ArrayList<RentRequest>
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

    fun getShowList(): ArrayList<ArrayList<RentRequest>>{
        return dividedShowList
    }

}