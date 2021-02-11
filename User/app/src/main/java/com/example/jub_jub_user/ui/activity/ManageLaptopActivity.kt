package com.example.jub_jub_user.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.jub_jub_user.R
import com.example.jub_jub_user.entity.singleton.ManageLaptopListManager
import com.example.jub_jub_user.ui.util.PageView.ManageLaptopList_PageView
import kotlinx.android.synthetic.main.activity_manage_laptop.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*

class ManageLaptopActivity : AppCompatActivity() {

    private lateinit var pageView: ManageLaptopList_PageView
    private var lastSize = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_laptop)

        pageView = ManageLaptopList_PageView(applicationContext, pageView_ManageLaptopActivity, ManageLaptopListManager.getShowList())
        pageView.initViewPager()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            Log.d("TestLog", "새로고침 완료!")

            refreshLayout.isRefreshing = false
        }
    }

    //타이틀 바 위젯들 온클릭 등록
    private fun setTitleBarItemsListener() {

        imageView_SearchMode_ManageLaptopActivity.setOnClickListener {
            setTitleBarSearchMode()
        }

        imageView_MyPage_ManageLaptopActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }

        imageView_BackArrow_ManageLaptopActivitySearchMode.setOnClickListener {
            editText_SearchText_ManageLaptopActivitySearchMode.setText("")
            setTitleBarViewMode()
        }

        setSearchFunction()
    }

    private fun setSearchFunction() {
        editText_SearchText_ManageLaptopActivitySearchMode.addTextChangedListener {

            search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            var currentSize = ManageLaptopListManager.getShowList().size

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
            ManageLaptopListManager.processShowList(word)
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
    }

    private fun setTitleBarSearchMode() {
        titleBar_ViewMode_ManageLaptopActivity.visibility = View.GONE
        titleBar_SearchMode_ManageLaptopActivity.visibility = View.VISIBLE
    }

    private fun setTitleBarViewMode() {
        titleBar_ViewMode_ManageLaptopActivity.visibility = View.VISIBLE
        titleBar_SearchMode_ManageLaptopActivity.visibility = View.GONE
        search("")
        pageView.syncPage()
    }
}