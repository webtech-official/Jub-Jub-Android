package com.example.jub_jub_admin.entity.singleton

import android.content.Context
import com.example.jub_jub_admin.data.local.db.LaptopStatusDB
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.ui.util.MyUtil

object ManageLaptopListManager {
    private lateinit var laptopStatusDB: LaptopStatusDB

    private var dividedShowList = ArrayList<ArrayList<LaptopStatus>>()


    fun setDummyData(context: Context){

        laptopStatusDB = LaptopStatusDB.getInstance(context)!!

        var r = Runnable {
            val laptopImage: String = MyUtil.getLaptopTestImage(context)

            laptopStatusDB.laptopStatusDAO().clear()

            laptopStatusDB.laptopStatusDAO().insert(LaptopStatus("한성 노트북", "노트북", 78, laptopImage,
                "몰라", "모른다구", "나도 몰라!", "ㅁㅁ", "ㅎㅎ"))
            laptopStatusDB.laptopStatusDAO().insert(LaptopStatus("맥북", "노트북", 78, laptopImage,
                "몰라", "모른다구", "나도 몰라!", "ㅁㅁ", "ㅎㅎ"))

        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

        processShowList("")

    }

    fun processShowList(key: String){
        var dataList = ArrayList<LaptopStatus>()


        var r = Runnable {
            if(key == ""){
                dataList = laptopStatusDB.laptopStatusDAO().getAll() as ArrayList<LaptopStatus>
                //Log.d("TestLog", " withoutkey ${dataList.size}")
                //Log.d("TestLog", "Key = \"\" , getoriginalDList = ${getOriginalDividedlaptopStatusList().size} ")
                //Log.d("TestLog", "Key = \"\" , originalDList = ${originalDividedlaptopStatusList.size} ")
            }
            else {
                dataList = laptopStatusDB.laptopStatusDAO().search("%$key%") as ArrayList<LaptopStatus>
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

    fun getShowList(): ArrayList<ArrayList<LaptopStatus>>{
        return dividedShowList
    }

}