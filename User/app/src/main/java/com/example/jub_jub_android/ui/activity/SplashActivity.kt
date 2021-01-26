package com.example.jub_jub_android.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.data.remote.NetRetrofit
import com.example.jup_jup_android.entity.dataclass.response.ResponseTest
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import com.example.jup_jup_android.entity.singleton.RentStatusListManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        startApp()
    }

    private fun startApp() {

        ItemStatusListManager.setDummyData(applicationContext)
        RentStatusListManager.setDummyDataList(applicationContext, 100)

        val response: Call<ResponseTest> = NetRetrofit.getServiceApi().getTest()

        response.enqueue(object: Callback<ResponseTest>{
            override fun onResponse(call: Call<ResponseTest>, response: Response<ResponseTest>) {
                Log.d("TestLog", "onResponse!")
                if(response.code() == 200){
                    Log.d("TestLog", "body.msg = ${response.body()?.msg}")

                }else{
                    Log.d("TestLog", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseTest>, t: Throwable) {
                Log.d("TestLog", t.message.toString())
                Log.d("TestLog", "Fail!")
            }
        })

        startActivity(Intent(applicationContext, LoginActivity ::class.java))
        finish()
    }
}