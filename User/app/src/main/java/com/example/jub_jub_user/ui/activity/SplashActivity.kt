package com.example.jub_jub_user.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jub_jub_user.R
import com.example.jub_jub_user.data.remote.NetRetrofit
import com.example.jub_jub_user.entity.dataclass.response.ResponseTest
import com.example.jub_jub_user.entity.singleton.ManageItemListManager
import com.example.jub_jub_user.entity.singleton.ManageLaptopListManager
import com.example.jub_jub_user.entity.singleton.RentRequestListManager
import com.example.jub_jub_user.entity.singleton.StudentRentStatusListManager
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
        setPermission()

        ManageItemListManager.setDummyData(applicationContext)
        StudentRentStatusListManager.setDummyDataList(applicationContext, 100)
        ManageLaptopListManager.setDummyData(applicationContext)

        RentRequestListManager.setDummyData(applicationContext)

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

    private fun setPermission() {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

    }
}