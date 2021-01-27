package com.example.jub_jub_admin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.singleton.ItemStatusListManager
import com.example.jub_jub_admin.ui.util.ItemStatusList_PageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var pageView: ItemStatusList_PageView
    private var lastSize = 0
    //마지막으로 뒤로가기 버튼 누른 시간
    var backKeyPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pageView = ItemStatusList_PageView(applicationContext, pageView_MainActivity, ItemStatusListManager.getShowList())
        pageView.initViewPager()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            Log.d("TestLog", "새로고침 완료!")

            refreshLayout.isRefreshing = false
        }

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

            var currentSize = ItemStatusListManager.getShowList().size

            var beforeTime = System.currentTimeMillis()

            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }

            var afterTime = System.currentTimeMillis()

            Log.d("TestLog", "syncTime = ${(afterTime - beforeTime)}")
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