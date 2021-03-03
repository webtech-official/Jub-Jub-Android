package com.example.jub_jub_admin.ui.manageEq

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.response.GetEquipmentResponse
import com.example.jub_jub_admin.entity.singleton.EquipmentManager
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.activity.MyPageActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {
    private val MODIFY_EQUIPMENT = 1042
    private lateinit var pageView: ManageItemList_PageView
    private var lastSize = 0
    //마지막으로 뒤로가기 버튼 누른 시간
    var backKeyPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataFromServer()

        setTitleBarItemsListener()
        refreshLayout.setOnRefreshListener {
            refresh()
        }
    }
    private fun refresh(){
        refreshLayout.isRefreshing = true
        getDataFromServer()
        pageView.syncPage()
        Log.d("TestLog", "새로고침 완료!")
        refreshLayout.isRefreshing = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TestLog_MainAc", "result = $resultCode, request = $requestCode")
        if(resultCode == RESULT_OK && requestCode == MODIFY_EQUIPMENT){
            Log.d("TestLog_MainAc", "새로고침 완료!")
            refresh()
        }
    }

    private fun getDataFromServer() {
        Log.d("TestLog_MainAc", "Token = ${TokenManager.getToken()}")
        val response: Call<GetEquipmentResponse> = NetRetrofit.getServiceApi().getAllEquipment(TokenManager.getToken())

        response.enqueue(object: Callback<GetEquipmentResponse> {
            override fun onResponse(call: Call<GetEquipmentResponse>, response: Response<GetEquipmentResponse>) {

                if(response.body()?.success == true){
                    Log.d("TestLog_MainAc", "Success! list.size = ${response.body()?.list?.size}")
                    EquipmentManager.setDataList(applicationContext, response.body()?.list!!)
                    setPageView()

                }else{
                    Log.d("TestLog_mainAc", "Fail! ${response.body()?.msg}")
                }

            }

            override fun onFailure(call: Call<GetEquipmentResponse>, t: Throwable) {
                Log.d("TestLog_mainAc", "Fail! ${t.message}")
            }
        })
    }

    private fun setPageView(){
        pageView = ManageItemList_PageView(applicationContext, pageView_MainActivity, EquipmentManager.getShowList())
        pageView.initViewPager()
    }

    //타이틀 바 위젯들 온클릭 등록
    private fun setTitleBarItemsListener() {

        imageView_SearchMode_MainActivity.setOnClickListener {
            setTitleBarSearchMode()
        }

        imageView_MyPage_MainActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }

        imageView_BackArrow_MainActivitySearchMode.setOnClickListener {
            editText_SearchText_MainActivitySearchMode.setText("")
            setTitleBarViewMode()
        }

        setSearchFunction()
    }

    private fun setSearchFunction() {
        editText_SearchText_MainActivitySearchMode.addTextChangedListener {

            search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            var currentSize = EquipmentManager.getShowList().size


            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }


        }
    }
    private fun search(word: String){
        var r = Runnable {
            EquipmentManager.processShowList(word)
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
    }

    private fun setTitleBarSearchMode() {
        titleBar_ViewMode_MainActivity.visibility = View.GONE
        titleBar_SearchMode_MainActivity.visibility = View.VISIBLE
    }

    private fun setTitleBarViewMode() {
        titleBar_ViewMode_MainActivity.visibility = View.VISIBLE
        titleBar_SearchMode_MainActivity.visibility = View.GONE
        search("")
        pageView.syncPage()
    }

    //뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {

        //1번 눌렀을 때
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(applicationContext, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        //2초 안에 2번 눌렀을 때 종료
        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishApp()
        }
    }

    private fun finishApp(){
        moveTaskToBack(true)                        // 태스크를 백그라운드로 이동
        finishAndRemoveTask()                        // 액티비티 종료 + 태스크 리스트에서 지우기
        Process.killProcess(Process.myPid())    // 앱 프로세스 종료
    }

}