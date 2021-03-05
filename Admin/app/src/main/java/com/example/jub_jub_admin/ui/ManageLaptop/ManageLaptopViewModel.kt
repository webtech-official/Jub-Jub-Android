package com.example.jub_jub_admin.ui.ManageLaptop

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jub_jub_admin.data.local.db.LaptopStatusDB
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.GetLaptopResponse
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.entity.singleton.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageLaptopViewModel: ViewModel() {

    private lateinit var laptopStatusDB: LaptopStatusDB

    companion object{
        var i : MutableLiveData<Int> = MutableLiveData()
        var list = MutableLiveData<ArrayList<ArrayList<LaptopStatus>>>()
        fun update() {
            i.value = i.value?.plus(1)
        }
    }

    init {
        i.value = 0
    }

    fun init(context: Context){
        laptopStatusDB = LaptopStatusDB.getInstance(context)!!

        list.value = ArrayList<ArrayList<LaptopStatus>>()

        getDataFromServer()
    }

    fun getDataFromServer(){

        val response: Call<GetLaptopResponse> = NetRetrofit.getServiceApi().getAllLaptop(TokenManager.getToken())

        response.enqueue(object : Callback<GetLaptopResponse>{
            override fun onResponse(call: Call<GetLaptopResponse>, response: Response<GetLaptopResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        setDataList(response.body()!!.list)
                    }else{
                        Log.d("TestLog_MLaptopVM", "실패 ${response.body()!!.msg}")
                    }

                }else{
                    Log.d("TestLog_MLaptopVM", "실패 ${response.body()!!.msg}")
                }
            }

            override fun onFailure(call: Call<GetLaptopResponse>, t: Throwable) {
                Log.d("TestLog_MLaptopVM", "서버통신 실패 ${t.message}")
            }

        })
    }

    fun setDataList(dataList: ArrayList<LaptopStatus>){
        Log.d("TestLog_ManLapVM", "setDataList 시작 ")
        val thread = Thread(Runnable {
            laptopStatusDB.laptopStatusDAO().clear()
            Log.d("TestLog_ManLapVM", "list Size ${dataList.size}")

            for(i in 0 until dataList.size){
                laptopStatusDB.laptopStatusDAO().insert(dataList[i])
                Log.d("TestLog_ManLapVM", "${laptopStatusDB.laptopStatusDAO().getAll()}")
            }
            Log.d("TestLog_ManLapVM", "setDataList 완료")
        })

        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }


        processShowList("")
        Log.d("TestLog_ManLapVM", "setDataList 끝")
    }

    fun processShowList(key: String){
        var dataList = ArrayList<LaptopStatus>()
        var array = ArrayList<ArrayList<LaptopStatus>>()

        var r = Runnable {
            if(key == ""){
                dataList = laptopStatusDB.laptopStatusDAO().getAll() as ArrayList<LaptopStatus>
                Log.d("TestLog_ManLapVM", "DB = ${laptopStatusDB.laptopStatusDAO().getAll()}")
                Log.d("TestLog_ManLapVM", "DataList = ${dataList}")
            }
            else {
                dataList = laptopStatusDB.laptopStatusDAO().search("%$key%") as ArrayList<LaptopStatus>
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
        Log.d("TestLog_ManLapVM", "process 후 사이즈 = ${list.value!!.size}")
    }

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

    fun getShowList(): ArrayList<ArrayList<LaptopStatus>>{
        return list.value!!
    }

}