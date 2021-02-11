package com.example.jub_jub_user.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.jub_jub_user.R
import com.example.jub_jub_user.entity.singleton.RentRequestListManager
import com.example.jub_jub_user.ui.util.PageView.AllowRentRequestList_PageView
import kotlinx.android.synthetic.main.activity_manage_rent_request.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*

class AllowRentRequestActivity : AppCompatActivity() {

    private lateinit var pageView: AllowRentRequestList_PageView
    private var lastSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_rent_request)

        pageView = AllowRentRequestList_PageView(this, pageView_AllowRentRequestActivity, RentRequestListManager.getShowList())
        pageView.initViewPager()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            Log.d("TestLog", "새로고침 완료!")

            refreshLayout.isRefreshing = false
        }

    }
    private fun setTitleBarItemsListener() {

        imageView_SearchMode_AllowRentRequestActivity.setOnClickListener {
            setTitleBarSearchMode()
        }

        imageView_MyPage_AllowRentRequestActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }

        imageView_BackArrow_AllowRentRequestActivitySearchMode.setOnClickListener {
            editText_SearchText_AllowRentRequestActivitySearchMode.setText("")
            setTitleBarViewMode()
        }

        setSearchFunction()
    }

    private fun setSearchFunction() {
        editText_SearchText_AllowRentRequestActivitySearchMode.addTextChangedListener {

            search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            var currentSize = RentRequestListManager.getShowList().size


            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }


        }
    }
    private fun search(word: String){
        var r = Runnable {
            RentRequestListManager.processShowList(word)
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }
    }

    private fun setTitleBarSearchMode() {
        titleBar_ViewMode_AllowRentRequestActivity.visibility = View.GONE
        titleBar_SearchMode_AllowRentRequestActivity.visibility = View.VISIBLE
    }

    private fun setTitleBarViewMode() {
        titleBar_ViewMode_AllowRentRequestActivity.visibility = View.VISIBLE
        titleBar_SearchMode_AllowRentRequestActivity.visibility = View.GONE
        search("")
        pageView.syncPage()
    }

}