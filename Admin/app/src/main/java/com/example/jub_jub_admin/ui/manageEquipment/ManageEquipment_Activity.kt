package com.example.jub_jub_admin.ui.manageEquipment

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.ui.activity.MyPageActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*


class ManageEquipment_Activity : AppCompatActivity() {

    private lateinit var viewModel: ManageEquipmentViewModel

    private lateinit var pageView: ManageEquipment_PageView

    private var lastSize = 0
    //마지막으로 뒤로가기 버튼 누른 시간
    var backKeyPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ManageEquipmentViewModel::class.java)

        pageView = ManageEquipment_PageView(applicationContext, pageView_MainActivity, viewModel)

        viewModel.init(applicationContext)

        pageView.initViewPager()

        //viewModel.getDataFromServer(true)

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            refresh()
        }

        ManageEquipmentViewModel.i.observe(this, {
            viewModel.getDataFromServer()
        })

        ManageEquipmentViewModel.list.observe(this, {
            pageView.syncPage()
        })
    }

    //region 새로고침
    private fun refresh(){
        refreshLayout.isRefreshing = true
        viewModel.getDataFromServer()
        Log.d("TestLog", "새로고침 완료!")
        refreshLayout.isRefreshing = false
    }
    //endregion

    //region 타이틀 바 위젯들 온클릭 등록
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
        //검색 기능 등록
        setSearchFunction()
    }
    //endregion

    //region EditText.changeListener에 검색 기능 등록
    private fun setSearchFunction() {
        editText_SearchText_MainActivitySearchMode.addTextChangedListener {

            viewModel.search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            var currentSize = viewModel.getShowList().size

            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }
        }
    }
    //endregion

    //region 타이틀 바 검색모드
    private fun setTitleBarSearchMode() {
        titleBar_ViewMode_MainActivity.visibility = View.GONE
        titleBar_SearchMode_MainActivity.visibility = View.VISIBLE
    }
    //endregion

    //region 타이틀 바 뷰 모드
    private fun setTitleBarViewMode() {
        titleBar_ViewMode_MainActivity.visibility = View.VISIBLE
        titleBar_SearchMode_MainActivity.visibility = View.GONE
        viewModel.search("")
        pageView.syncPage()

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