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

object RentStatusListManager {

    private lateinit var rentStatusList: ArrayList<RentStatus>

    private var originalDividedRentStatusList = ArrayList<ArrayList<RentStatus>>()

    private var dividedReturnedList = ArrayList<ArrayList<RentStatus>>()
    private var dividedRentingList = ArrayList<ArrayList<RentStatus>>()
    private var dividedOverDueList = ArrayList<ArrayList<RentStatus>>()

    private var dividedShowList = ArrayList<ArrayList<RentStatus>>()


//    var userId: String,
//    var name: String,
//    var category: String,
//    var count: Int,
//    var image: String,
//    var status: String
    

    fun setDummyDataList(context: Context, cnt: Int){
//        /var RentStatusData = RentStatus(BitmapFactory.decodeResource(context.resources, R.drawable.imageex), "DC모터", "모터", 5)

        var tempList = ArrayList<RentStatus>()

        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imageex)
        val byteStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
        val byteArray: ByteArray = byteStream.toByteArray()
        val baseString: String = Base64.encodeToString(byteArray, Base64.DEFAULT)

        for(i in 0..cnt){
            when(i%3){

                0 -> tempList.add(RentStatus("1","DC모터", "모터", i, baseString,"반납"))
                1 -> tempList.add(RentStatus("1","DC모터", "모터", i, baseString,"대여"))
                2 -> tempList.add(RentStatus("1","DC모터", "모터", i, baseString,"연체"))
            }
        }


        setRentStatusList(tempList)

        initShowModeLists(tempList, dividedReturnedList, "반납")
        initShowModeLists(tempList, dividedRentingList, "대여")
        initShowModeLists(tempList, dividedOverDueList, "연체")

        Log.d("TestLog", "반납 =  ${dividedReturnedList.size}")
        Log.d("TestLog", "대여 =  ${dividedRentingList.size}")
        Log.d("TestLog", "연체 =  ${dividedOverDueList.size}")
        Log.d("TestLog", "show =  ${getShowList().size}")
    }
    private fun initShowModeLists(dataList: ArrayList<RentStatus>, pagingList: ArrayList<ArrayList<RentStatus>>, status: String){

        pagingList.clear()

        var page = 0
        var cnt = 0
        pagingList.add(ArrayList())

        for(i in 0 until dataList.size){

            if(dataList[i].status == status){
                if(cnt == 5){
                    pagingList.add(ArrayList())
                    cnt = 0
                    page++
                }
                else{
                pagingList[page].add(dataList[i])
                cnt++
                }
            }
        }
    }

    
    @JvmName("getRentStatusList1")
    fun getRentStatusList(): ArrayList<RentStatus>{
        return rentStatusList
    }

    fun addRentStatusItem(item: RentStatus){
        rentStatusList.add(item)
    }

    @JvmName("setRentStatusList1")
    fun setRentStatusList(dataList: ArrayList<RentStatus>){

        //rentStatusList = dataList

        var tempList = ArrayList<RentStatus>()

        originalDividedRentStatusList.clear()
        for(i in 0..dataList.size/5){
            //Log.d("TestLog","i = $i")
            originalDividedRentStatusList.add(ArrayList())
            for(j in 0 until 5){
                //Log.d("TestLog","j = $j")
                if(i*5 + j >= dataList.size){
                    break
                }else{
                    originalDividedRentStatusList[i].add(dataList[i * 5 + j])
                }
            }
        }
        setShowedList(originalDividedRentStatusList)
    }



    fun getShowList(): ArrayList<ArrayList<RentStatus>> {
        return dividedShowList
    }

    private fun setShowedList(dataList: ArrayList<ArrayList<RentStatus>>) {
        dividedShowList = dataList
    }


    fun showReturnedDividedList(){
        dividedShowList = dividedReturnedList
    }

    fun showOverDueDividedList(){
        dividedShowList = dividedOverDueList
    }

    fun showRentingDividedList(){
        dividedShowList = dividedRentingList
    }

    fun showOriginalDividedList(){
        dividedShowList = originalDividedRentStatusList
    }


}