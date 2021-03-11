package com.example.jub_jub_android.ui.view.myrentstatus

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jub_jub_android.data.local.MyEquipmentDB
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.dataclass.MyEquipment
import com.example.jub_jub_android.entity.dataclass.response.MyEquipmentDetailInfo
import com.example.jub_jub_android.entity.dataclass.response.MyEquipmentResponse
import com.example.jub_jub_android.entity.singleton.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRentList_ViewModel: ViewModel() {

    private lateinit var myEquipmentDB : MyEquipmentDB

    var showStatus = MutableLiveData<String>()

    var list = MutableLiveData<ArrayList<ArrayList<MyEquipment>>>()

    init {
        showStatus.value = "전체"
        list.value = ArrayList<ArrayList<MyEquipment>>()
    }

    fun init(context: Context){

        myEquipmentDB = MyEquipmentDB.getInstance(context)!!
        getMyEquipmentData(context)

    }

    fun getMyEquipmentData(context: Context) {
        val response: Call<MyEquipmentResponse> = NetRetrofit.getServiceApi().getMyEquipmentData(TokenManager.getToken())

        response.enqueue(object: Callback<MyEquipmentResponse>{

            override fun onResponse(call: Call<MyEquipmentResponse>, response: Response<MyEquipmentResponse>) {
                if(response.isSuccessful && response.body()!!.success){
                    setDataList(response.body()?.list!!)
                }else{
                    Toast.makeText(context, response.body()?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyEquipmentResponse>, t: Throwable) {
                Toast.makeText(context, t.message!!, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun setDataList(dataList: ArrayList<MyEquipmentDetailInfo>){

        val thread = Thread(Runnable {
            myEquipmentDB.myEquipmentDAO().clear()

            for(i in 0 until dataList.size){
                val data = dataList[i].equipment

                myEquipmentDB.myEquipmentDAO().insert(
                    MyEquipment(
                        data.name,
                        data.content,
                        dataList[i].amount,
                        data.img_equipment,
                        getStatus(dataList[i])
                    ))
            }
        })

        thread.start()

        try {
            thread.join()
        } catch (e:InterruptedException){Log.d("TestLog_MyEqManager", "데이터 입력 에러 발생 ${e.message}")}

        processShowList("")
    }

    fun processShowList(key: String){
        var dataList = ArrayList<MyEquipment>()
        var array = ArrayList<ArrayList<MyEquipment>>()

        var r = Runnable {

            if(key == "전체" || key == ""){
                dataList = myEquipmentDB.myEquipmentDAO().getAll() as ArrayList<MyEquipment>
                Log.d("TestLog", " rent withoutkey ${dataList.size}")
            }
            else {
                dataList = myEquipmentDB.myEquipmentDAO().search("%$key%") as ArrayList<MyEquipment>
                Log.d("TestLog", " rent $key = ${dataList.size}")
            }

            array.clear()
            var page = 0
            var cnt = 0
            array.add(ArrayList())

            for (i in 0 until dataList.size) {
                if (cnt == 5) {
                    array.add(ArrayList())
                    cnt = 0
                    page++
                }
                array[page].add(dataList[i])
                cnt++
            }
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
        list.postValue(array)
    }

    private fun getStatus(data: MyEquipmentDetailInfo): String {
        return when(data.equipmentEnum){
            "ROLE_Waiting" -> "대기"
            "ROLE_Accept" -> "승인"
            "ROLE_Reject" -> "거절"
            "ROLE_Rental" -> "대여"
            "ROLE_Return" -> "반납"
            //대여, 반납, 연체 추가
            else -> "?"
        }
    }

    //region change Rent Status View Mode
    fun changeRSVM(){
        showStatus.value =
            when(showStatus.value){
                "전체" -> "대기"
                "대기" -> "대여"
                "대여" -> "승인"
                "승인" -> "반납"
                "반납" -> "거절"
                "거절" -> "전체"
                else -> "?"
            }
    }
    //endregion

    fun getDataList(): ArrayList<ArrayList<MyEquipment>> {
        return list.value!!
    }
}