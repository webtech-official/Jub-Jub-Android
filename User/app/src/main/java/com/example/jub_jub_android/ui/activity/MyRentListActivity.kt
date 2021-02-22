package com.example.jub_jub_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.MyEquipmentDB
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.dataclass.response.MyEquipmentResponse
import com.example.jub_jub_android.entity.singleton.MyEquipmentListManager
import com.example.jub_jub_android.entity.singleton.MyEquipmentListManager.setDataList
import com.example.jub_jub_android.entity.singleton.TokenManager
import com.example.jub_jub_android.ui.util.MyRentList_PageView
import kotlinx.android.synthetic.main.activity_my_rental_list.*
import kotlinx.android.synthetic.main.layout_pageview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRentListActivity : AppCompatActivity() {

    lateinit var pageView : MyRentList_PageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_rental_list)

        getDataFromServer()

        setTitleBarItemsOnclick()

        refreshLayout.setOnRefreshListener {
            getDataFromServer()

            pageView.syncPage()
            Toast.makeText(applicationContext, "새로고침 완료", Toast.LENGTH_SHORT).show()
            refreshLayout.isRefreshing = false
        }
    }

    private fun setPageView() {
        pageView = MyRentList_PageView(applicationContext, pageView_MyRentalListActivity, MyEquipmentListManager.getShowList())
        pageView.initViewPager()
    }

    private fun getDataFromServer() {
        val response: Call<MyEquipmentResponse> = NetRetrofit.getServiceApi().getMyEquipmentData(TokenManager.getToken())

        response.enqueue(object: Callback<MyEquipmentResponse>{
            override fun onResponse(
                call: Call<MyEquipmentResponse>,
                response: Response<MyEquipmentResponse>
            ) {
                if(response.body()?.success!!){
                    setDataList(applicationContext, response.body()?.list!!)
                    setPageView()
                    MyEquipmentListManager.processShowList("${textView_ShowMode_MyRentalListActivity.text}")

                }else{
                    Toast.makeText(applicationContext, "서버에서 데이터를 받아오는데 실패했습니다!", Toast.LENGTH_SHORT).show()
                }
                Log.d("TestLog_MyEquipList", "isSuc = ${response.isSuccessful}")
                Log.d("TestLog_MyEquipList", "size = ${response.body()?.list?.size}")

            }

            override fun onFailure(call: Call<MyEquipmentResponse>, t: Throwable) {
                Log.d("TestLog_MyEquipList", "t = ${t}")
                Log.d("TestLog_MyEquipList", "t.localizedMessage = ${t.localizedMessage}")
                Log.d("TestLog_MyEquipList", "call = ${call}")
            }
        })
    }

    private fun setTitleBarItemsOnclick() {

        imageView_BackArrow_MyRentalListActivity.setOnClickListener {
            back()
        }

        textView_ShowMode_MyRentalListActivity.setOnClickListener {
            when(textView_ShowMode_MyRentalListActivity.text.toString()){
                "전체" -> setShowMode("대기")
                "대기" -> setShowMode("대여")
                "대여" -> setShowMode("반납")
                "반납" -> setShowMode("연체")
                "연체" -> setShowMode("거절")
                "거절" -> setShowMode("전체")
            }

            //RentAdapter.getViewPagerAdapter().notifyDataSetChanged()
            //RentAdapter.getRecyclerAdapter().notifyDataSetChanged()
            pageView.syncPage()
        }
    }
    private fun setShowMode(text: String){
        textView_ShowMode_MyRentalListActivity.text = text
        MyEquipmentListManager.processShowList(text)
    }

    private fun back(){
        MyEquipmentListManager.processShowList("전체")
        pageView.syncPage()
        finish()
    }

    override fun onBackPressed() {
        back()
    }
}