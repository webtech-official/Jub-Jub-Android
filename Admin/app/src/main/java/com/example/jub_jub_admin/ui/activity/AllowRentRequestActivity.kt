package com.example.jub_jub_admin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.response.RentRequestResponse
import com.example.jub_jub_admin.entity.singleton.RentRequestListManager
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.util.PageView.AllowRentRequestList_PageView
import kotlinx.android.synthetic.main.activity_manage_rent_request.*
import kotlinx.android.synthetic.main.layout_pageview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AllowRentRequestActivity : AppCompatActivity() {

    private lateinit var pageView: AllowRentRequestList_PageView
    private var lastSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_rent_request)

        getDataFromServer()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            Log.d("TestLog_AllowRequest", "새로고침 완료!")
            Toast.makeText(applicationContext, "새로고침 완료", Toast.LENGTH_SHORT).show()
            refreshLayout.isRefreshing = false
        }

    }

    private fun getDataFromServer() {
        val response: Call<RentRequestResponse> = NetRetrofit.getServiceApi().getRentRequest(TokenManager.getToken())

        response.enqueue(object: Callback<RentRequestResponse> {
            override fun onResponse(call: Call<RentRequestResponse>, response: Response<RentRequestResponse>) {
                if(response.isSuccessful){
                    if(response.body()?.success!!){
                        Log.d("TestLog_AllowRequest", "${response.body()?.list}")
                        RentRequestListManager.setDataList(response.body()?.list!!)
                        setPageView()
                    }else{
                        Log.d("TestLog_AllowRequest", response.body()?.msg!!)
                        Log.d("TestLog_AllowRequest", "")
                    }
                }else{
                    Log.d("TestLog_AllowRequest", response.body()?.msg!!)
                    Log.d("TestLog_AllowRequest", "실패")
                }

            }

            override fun onFailure(call: Call<RentRequestResponse>, t: Throwable) {
                Log.d("TestLog_AllowRequest", t.message!!)
                Log.d("TestLog_AllowRequest", "실패")
            }

        })
    }

    private fun setPageView(){
        pageView = AllowRentRequestList_PageView(this, pageView_AllowRentRequestActivity, RentRequestListManager.getShowList())
        pageView.initViewPager()
    }


    private fun setTitleBarItemsListener() {
        imageView_MyPage_AllowRentRequestActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }
    }


}