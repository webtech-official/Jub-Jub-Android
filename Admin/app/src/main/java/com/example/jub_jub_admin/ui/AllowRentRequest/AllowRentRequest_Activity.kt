package com.example.jub_jub_admin.ui.AllowRentRequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.ui.activity.MyPageActivity
import kotlinx.android.synthetic.main.activity_manage_rent_request.*
import kotlinx.android.synthetic.main.layout_pageview.*

class AllowRentRequest_Activity : AppCompatActivity() {

    private lateinit var viewModel: AllowRentRequest_ViewModel
    private lateinit var pageView: AllowRentRequest_PageView

    var backKeyPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_rent_request)

        init()

    }

    //region init
    private fun init() {
        //ViewModel 객체 등록 -> pageView 객체 등록 -> viewModel.init() -> pageView.init() 순서대로 해야 함
        viewModel = ViewModelProvider(this).get(AllowRentRequest_ViewModel::class.java)

        pageView = AllowRentRequest_PageView(this, pageView_AllowRentRequestActivity, viewModel)

        viewModel.init(applicationContext)

        pageView.initViewPager()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            refresh()
        }

        AllowRentRequest_ViewModel.i.observe(this, {
            viewModel.getRentRequest(applicationContext)
        })

        AllowRentRequest_ViewModel.list.observe(this, {
            pageView.syncPage()
        })
    }
    //endregion

    //region 새로고침
    private fun refresh(){
        refreshLayout.isRefreshing = true
        viewModel.getRentRequest(applicationContext)
        refreshLayout.isRefreshing = false
        Toast.makeText(applicationContext, "새로고침 완료", Toast.LENGTH_SHORT).show()
    }
    //endregion

    //region MyPage 버튼 눌렀을 때
    private fun setTitleBarItemsListener() {
        imageView_MyPage_AllowRentRequestActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }
    }
    //endregion

    //region 뒤로가기 버튼 눌렀을 때
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
    //endregion

    //region 앱 완전 종료
    private fun finishApp(){
        moveTaskToBack(true)                        // 태스크를 백그라운드로 이동
        finishAndRemoveTask()                        // 액티비티 종료 + 태스크 리스트에서 지우기
        Process.killProcess(Process.myPid())    // 앱 프로세스 종료
    }
    //endregion


}