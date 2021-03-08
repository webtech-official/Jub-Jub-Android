package com.example.jub_jub_admin.entity.singleton

import android.util.Log
import android.widget.Toast
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.response.GETLaptopSpecResponse
import com.example.jub_jub_admin.entity.dataclass.response.LaptopSpec
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LaptopSpecManager {

    var laptopSpecList = ArrayList<LaptopSpec>()


    fun getLaptopSpec(){
        val response: Call<GETLaptopSpecResponse> = NetRetrofit.getServiceApi().getLaptopSpec(TokenManager.getToken())

        response.enqueue(object : Callback<GETLaptopSpecResponse>{
            override fun onResponse(call: Call<GETLaptopSpecResponse>, response: Response<GETLaptopSpecResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        laptopSpecList = response.body()!!.list
                        Log.d("TestLog_LaptopSpecManager", "laptopSpecListFromServer = $laptopSpecList")
                    }
                }
            }

            override fun onFailure(call: Call<GETLaptopSpecResponse>, t: Throwable) {

            }

        })
    }

    fun getSpecList(): ArrayList<LaptopSpec> {
        if(laptopSpecList.size == 0){
            getLaptopSpec()
        }
        Log.d("TestLog_LaptopSpecManager", "return SpecList ${laptopSpecList}")
        return laptopSpecList
    }

    fun getSpecNameList(): ArrayList<String> {
        if(laptopSpecList.size == 0){
            getLaptopSpec()
        }
        var arr = ArrayList<String>()

        for(i in 0 until laptopSpecList.size){
            arr.add("${laptopSpecList[i].specIdx}기 노트북")
        }
        Log.d("TestLog_LaptopSpecManager", "return NameList ${arr}")
        return arr
    }

}