package com.example.jub_jub_admin.ui.manageEq

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jub_jub_admin.data.local.db.EquipmentDB
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.Equipment
import com.example.jub_jub_admin.entity.dataclass.response.GetEquipmentResponse
import com.example.jub_jub_admin.entity.singleton.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep

class ManageEquipmentViewModel: ViewModel() {

    private lateinit var equipmentDB: EquipmentDB

    companion object{
        var i : MutableLiveData<Int> = MutableLiveData()
        var list = MutableLiveData<ArrayList<ArrayList<Equipment>>>()
        fun update() {
            i.value = i.value?.plus(1)
        }

    }

    init {
        i.value = 0
    }

    fun init(context: Context){
        equipmentDB = EquipmentDB.getInstance(context)!!

        list.value = ArrayList<ArrayList<Equipment>>()
        getDataFromServer()
        //context 저장
        //this.context = context
        //DB객체 생성
        //setDummyData()

        Log.d("TestLog_MainViewModel", "${list.value}")
    }

    fun getDataFromServer() {
        Log.d("TestLog_MainViewModel", "getDataFromServer 시작")
        val response: Call<GetEquipmentResponse> = NetRetrofit.getServiceApi().getAllEquipment(
            TokenManager.getToken())

        response.enqueue(object: Callback<GetEquipmentResponse> {
            override fun onResponse(call: Call<GetEquipmentResponse>, response: Response<GetEquipmentResponse>) {

                if(response.body()?.success == true){
                    Log.d("TestLog_MainViewModel", "getDataFromServer 성공 list.size = ${response.body()?.list?.size}")
                    setDataList(response.body()?.list!!)

                }else{
                    Log.d("TestLog_MainViewModel", "Fail! ${response.body()?.msg}")
                }
            }

            override fun onFailure(call: Call<GetEquipmentResponse>, t: Throwable) {
                Log.d("TestLog_MainViewModel", "Fail! ${t.message}")
                Log.d("TestLog_MainViewModel", "getDataFromServer 실패")
            }

        })
    }

    fun setDataList(dataList: ArrayList<Equipment>){
        Log.d("TestLog_MainViewModel", "setDataList 시작 ")
        val thread = Thread(Runnable {
            equipmentDB.equipmentDAO().clear()
            Log.d("TestLog_MainViewModel", "list Size ${dataList.size}")

            for(i in 0 until dataList.size){
                equipmentDB.equipmentDAO().insert(dataList[i])
                Log.d("TestLog_MainViewModel", "dL.size = ${dataList.size}")

            }
            Log.d("TestLog_MainViewModel", "setDataList 완료")
        })

        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }


        processShowList("")
        Log.d("TestLog_MainViewModel", "setDataList 끝")
    }

    fun processShowList(key: String){
        Log.d("TestLog_MainViewModel", "processShowList 시작")
        var dataList = ArrayList<Equipment>()
        var array = ArrayList<ArrayList<Equipment>>()
        val r = Runnable {
            if(key == ""){
                dataList = equipmentDB.equipmentDAO().getAll() as ArrayList<Equipment>
                Log.d("TestLog_MainViewModel", "dL.size = ${dataList.size}")
            }
            else {
                dataList = equipmentDB.equipmentDAO().search("%$key%") as ArrayList<Equipment>
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
            Log.d("TestLog_MainViewModel", "process array $array")
            Log.d("TestLog_MainViewModel", "process list ${list.value}")
        }
        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

        list.value = array
        Log.d("TestLog_MainViewModel", "process list ${list.value}")

    }

    @JvmName("getShowList1")
    fun getShowList(): ArrayList<ArrayList<Equipment>> {

        val arr = list.value!!
        return arr
    }

}