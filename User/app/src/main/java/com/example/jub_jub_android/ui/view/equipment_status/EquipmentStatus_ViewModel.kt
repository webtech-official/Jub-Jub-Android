package com.example.jub_jub_android.ui.view.equipment_status

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jub_jub_android.data.local.EquipmentStatusDB
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.entity.dataclass.response.EquipmentResponse
import com.example.jub_jub_android.entity.singleton.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EquipmentStatus_ViewModel: ViewModel() {

    private lateinit var equipmentStatusDB: EquipmentStatusDB


    companion object{
        var list = MutableLiveData<ArrayList<ArrayList<Equipment>>>()

        private var i = MutableLiveData<Int>()

        fun update(){
            i.value = i.value!! + 1
        }
    }


    fun init(context: Context){
        equipmentStatusDB = EquipmentStatusDB.getInstance(context)!!

        list.value = ArrayList<ArrayList<Equipment>>()
        i.value = 0

        getEquipmentData(context)

        Log.d("TestLog_MainViewModel", "${list.value}")
    }



    //region 기자재 정보 가져오기
    fun getEquipmentData(context: Context) {

        val response: Call<EquipmentResponse> = NetRetrofit.getServiceApi().getEquipmentData(TokenManager.getToken())

        response.enqueue(object : Callback<EquipmentResponse> {
            override fun onResponse(call: Call<EquipmentResponse>, response: Response<EquipmentResponse>) {
                if(response.body()!!.success){
                    setDataList(response.body()?.list!!)
                } else{
                    Toast.makeText(context, response.body()?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EquipmentResponse>, t: Throwable) {
                Toast.makeText(context, t.message!!, Toast.LENGTH_SHORT).show()
            }

        })
    }
    //endregion

    //region setDataList
    fun setDataList(dataList: ArrayList<Equipment>){
        val thread = Thread(Runnable {
            equipmentStatusDB.equipmentStatusDAO().clear()

            for(i in 0 until dataList.size){
                equipmentStatusDB.equipmentStatusDAO().insert(dataList[i])
            }
        })

        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

        processDataList("")
    }
    //endregion

    //region processDataList
    private fun processDataList(key: String){
        var dataList = ArrayList<Equipment>()
        var array = ArrayList<ArrayList<Equipment>>()

        var r = Runnable {
            if(key == ""){
                dataList = equipmentStatusDB.equipmentStatusDAO().getAll() as ArrayList<Equipment>
            } else {
                dataList = equipmentStatusDB.equipmentStatusDAO().search("%$key%") as ArrayList<Equipment>
            }

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
        Log.d("TestLog_MainViewModel", "${list.value}")

    }
    //endregion

    //region 검색
    fun search(word: String){
        processDataList(word)
    }
    //endregion

    fun getDataList(): ArrayList<ArrayList<Equipment>> {
        return list.value!!
    }

}