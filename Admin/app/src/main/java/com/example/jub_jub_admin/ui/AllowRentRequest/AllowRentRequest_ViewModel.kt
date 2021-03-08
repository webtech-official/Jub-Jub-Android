package com.example.jub_jub_admin.ui.AllowRentRequest

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.response.RentRequestDetailInfo
import com.example.jub_jub_admin.entity.dataclass.response.RentRequestResponse
import com.example.jub_jub_admin.entity.singleton.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllowRentRequest_ViewModel: ViewModel() {

    companion object{
        var i : MutableLiveData<Int> = MutableLiveData()
        var list = MutableLiveData<ArrayList<ArrayList<RentRequestDetailInfo>>>()
        fun update() {
            i.value = i.value?.plus(1)
        }
    }
    init {
        i.value = 0
    }

    fun init(context: Context) {
        list.value = ArrayList<ArrayList<RentRequestDetailInfo>>()
        getRentRequest(context)
    }

    fun getRentRequest(context: Context) {
        val response: Call<RentRequestResponse> = NetRetrofit.getServiceApi().getRentRequest(TokenManager.getToken())

        response.enqueue(object: Callback<RentRequestResponse> {
            override fun onResponse(call: Call<RentRequestResponse>, response: Response<RentRequestResponse>) {
                if(response.isSuccessful && response.body()!!.success){
                        Log.d("TestLog_AllowRequestViewModel", "${response.body()?.list}")
                        setDataList(response.body()!!.list.reversed() as ArrayList<RentRequestDetailInfo>)

                }else{
                    Toast.makeText(context, response.body()?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RentRequestResponse>, t: Throwable) {
                Toast.makeText(context, t.message!!, Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun setDataList(dataList: ArrayList<RentRequestDetailInfo>){
        processShowList(dataList)
    }

    private fun processShowList(dataList: ArrayList<RentRequestDetailInfo>) {
        var arr = ArrayList<ArrayList<RentRequestDetailInfo>>()
        var r = Runnable {
            
            var page = 0
            var cnt = 0
            arr.add(ArrayList())

            for (i in 0 until dataList.size) {
                if(dataList[i].equipmentEnum == "ROLE_Waiting"){
                    if (cnt == 5) {
                        arr.add(ArrayList())
                        cnt = 0
                        page++
                    }
                    arr[page].add(dataList[i])
                    cnt++
                }
            }

        }
        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
        
        list.postValue(arr)
    }

    fun getShowList(): ArrayList<ArrayList<RentRequestDetailInfo>> {
        return list.value!!
    }

}