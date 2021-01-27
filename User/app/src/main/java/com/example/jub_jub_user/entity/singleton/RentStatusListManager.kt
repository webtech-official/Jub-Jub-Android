package com.example.jub_jub_user.entity.singleton

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jub_jub_user.data.local.RentStatusDB
import com.example.jub_jub_user.entity.dataclass.RentStatus
import java.io.ByteArrayOutputStream

object RentStatusListManager {

    private lateinit var rentStatusDB : RentStatusDB

    private var dividedShowList = ArrayList<ArrayList<RentStatus>>()



    fun setDummyDataList(context: Context, cnt: Int){
        rentStatusDB = RentStatusDB.getInstance(context)!!
        var r = Runnable {
            val byteStream = ByteArrayOutputStream()
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imageex)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
            val byteArray: ByteArray = byteStream.toByteArray()
            val baseString = Base64.encodeToString(byteArray, Base64.DEFAULT)

            rentStatusDB.rentStatusDAO().clear()
            for(i in 0..cnt){
                when(i%3){
                    0 -> rentStatusDB.rentStatusDAO().insert(RentStatus("1","DC모터", "모터", i, baseString,"반납"))
                    1 -> rentStatusDB.rentStatusDAO().insert(RentStatus("1","DC모터", "모터", i, baseString,"대여"))
                    2 -> rentStatusDB.rentStatusDAO().insert(RentStatus("1","DC모터", "모터", i, baseString,"연체"))
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
        var dataList = ArrayList<RentStatus>()

        var r = Runnable {

            if(key == "전체"){
                dataList = rentStatusDB.rentStatusDAO().getAll() as ArrayList<RentStatus>
                Log.d("TestLog", " rent withoutkey ${dataList.size}")
            }
            else {
                dataList = rentStatusDB.rentStatusDAO().search("%$key%") as ArrayList<RentStatus>
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

    fun getShowList(): ArrayList<ArrayList<RentStatus>> {
        return dividedShowList
    }
}