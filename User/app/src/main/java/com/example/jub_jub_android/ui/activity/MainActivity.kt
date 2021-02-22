package com.example.jub_jub_android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.dataclass.response.EquipmentResponse
import com.example.jub_jub_android.entity.singleton.ItemStatusListManager
import com.example.jub_jub_android.entity.singleton.ItemStatusListManager.setDataList
import com.example.jub_jub_android.entity.singleton.TokenManager
import com.example.jub_jub_android.ui.util.ItemStatusList_PageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var pageView: ItemStatusList_PageView
    private var lastSize = 0
    //마지막으로 뒤로가기 버튼 누른 시간
    var backKeyPressedTime : Long = 0
    var isViewMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //test()
        getDataFromServer()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            getDataFromServer()
            Log.d("TestLog", "새로고침 완료!")
            pageView.syncPage()
            refreshLayout.isRefreshing = false
        }

    }

    fun setPageView(){
        Log.d("TestLog_Main", "setPageView 시작 ")
            pageView = ItemStatusList_PageView(applicationContext, pageView_MainActivity, ItemStatusListManager.getShowList())
            pageView.initViewPager()
    }


    private fun getDataFromServer() {
        Log.d("TestLog_Main", "Token = ${TokenManager.getToken()}")

        val response: Call<EquipmentResponse> = NetRetrofit.getServiceApi().getEquipmentData(TokenManager.getToken())

        response.enqueue(object :Callback<EquipmentResponse> {
            override fun onResponse(call: Call<EquipmentResponse>, response: Response<EquipmentResponse>) {
                Log.d("TestLog_Main", "isSuc = ${response.isSuccessful}")
                Log.d("TestLog_Main", "re.body.suc = ${response.body()?.success}")
                if(response.body()!!.success){
                    setDataList(applicationContext, response.body()?.list!!)
                    setPageView()
                    Log.d("TestLog_Main", "DataList.size = ${ItemStatusListManager.getShowList().size}")
                }
                else{
                    Log.d("TestLog_Main", "body = ${response.body()}")
                    Toast.makeText(applicationContext, "실패했습니다!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EquipmentResponse>, t: Throwable) {
                Log.d("TestLog",  "Fail! ${t.message}")
                Log.d("TestLog",  "Fail! ${t.localizedMessage}")
            }

        })

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

            Log.d("TestLog_Main", "text = ${editText_SearchText_MainActivitySearchMode.text}")

            search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            //이거 검색 최적화 알고리즘 수정 해야함
            var currentSize = ItemStatusListManager.getShowList()[0].size

            Log.d("TestLog_Main", "sie = $currentSize")

            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }

        }
    }
    private fun search(word: String){
        var r = Runnable {
            ItemStatusListManager.processShowList(word)
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
    }

    private fun setTitleBarSearchMode() {
        isViewMode = false
        titleBar_ViewMode_MainActivity.visibility = View.GONE
        titleBar_SearchMode_MainActivity.visibility = View.VISIBLE
    }

    private fun setTitleBarViewMode() {
        isViewMode = true
        titleBar_ViewMode_MainActivity.visibility = View.VISIBLE
        titleBar_SearchMode_MainActivity.visibility = View.GONE
        search("")
        pageView.syncPage()
    }

    //뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {

        if(!isViewMode){
            setTitleBarViewMode()
        }else{
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
    }

    private fun finishApp(){
        moveTaskToBack(true)                        // 태스크를 백그라운드로 이동
        finishAndRemoveTask()                        // 액티비티 종료 + 태스크 리스트에서 지우기
        Process.killProcess(Process.myPid())    // 앱 프로세스 종료
    }

}