package com.example.jub_jub_admin.ui.manageEquipment

import android.content.Context
import android.util.Log
import android.widget.Toast
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

class ManageEquipment_ViewModel: ViewModel() {

    private lateinit var equipmentDB: EquipmentDB

    companion object{
        var i : MutableLiveData<Int> = MutableLiveData()
        var list = MutableLiveData<ArrayList<ArrayList<Equipment>>>()
        fun update() {
            i.value = i.value?.plus(1)
            Log.d("TestLog_MaEqVM", "update!")
        }
    }

    init {
        i.value = 0
    }

    fun init(context: Context){
        equipmentDB = EquipmentDB.getInstance(context)!!

        list.value = ArrayList<ArrayList<Equipment>>()
        getEquipmentData(context)

        Log.d("TestLog_MainViewModel", "${list.value}")
    }

    fun getEquipmentData(context: Context) {
        Log.d("TestLog_MainViewModel", "getDataFromServer 시작")
        val response: Call<GetEquipmentResponse> = NetRetrofit.getServiceApi().getAllEquipment(
            TokenManager.getToken())

        response.enqueue(object: Callback<GetEquipmentResponse> {
            override fun onResponse(call: Call<GetEquipmentResponse>, response: Response<GetEquipmentResponse>) {

                if(response.isSuccessful && response.body()?.success == true){
                    Log.d("TestLog_MainViewModel", "getDataFromServer 성공 list.size = ${response.body()?.list?.size}")
                    setDataList(response.body()?.list!!)
                }else{
                    Toast.makeText(context, response.body()?.msg, Toast.LENGTH_SHORT).show()                }
            }

            override fun onFailure(call: Call<GetEquipmentResponse>, t: Throwable) {
                Toast.makeText(context, t.message!!, Toast.LENGTH_SHORT).show()
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
        }
        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }

        //list.value =
        list.postValue(array)
        Log.d("TestLog_MainViewModel", "process list ${list.value}")
    }

    //region 검색
    fun search(word: String){
        var r = Runnable {
            processShowList(word)
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
    }
    //endregion

    @JvmName("getShowList1")
    fun getShowList(): ArrayList<ArrayList<Equipment>> {

        return list.value!!
    }

}