package com.example.jub_jub_admin.ui.ManageLaptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.ui.activity.MyPageActivity
import kotlinx.android.synthetic.main.activity_manage_laptop.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*

class ManageLaptop_Activity : AppCompatActivity() {

    private lateinit var pageView: ManageLaptop_PageView
    private lateinit var viewModel: ManageLaptop_ViewModel

    var backKeyPressedTime : Long = 0

    private var lastSize = 0
    private var isViewMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_laptop)

        viewModel = ViewModelProvider(this).get(ManageLaptop_ViewModel::class.java)

        viewModel.init(applicationContext)

        pageView = ManageLaptop_PageView(applicationContext, pageView_ManageLaptopActivity, viewModel)

        pageView.initViewPager()

        setTitleBarItemsListener()

        refreshLayout.setOnRefreshListener {
            refresh()
            Log.d("TestLog_MLapAc","${viewModel.getShowList().size}")
        }

        ManageLaptop_ViewModel.i.observe(this, {
            viewModel.getDataFromServer(applicationContext)
        })

        ManageLaptop_ViewModel.list.observe(this, {
            pageView.syncPage()
        })
    }

    private fun refresh() {
        refreshLayout.isRefreshing = true
        viewModel.getDataFromServer(applicationContext)
        Log.d("TestLog", "새로고침 완료!")
        refreshLayout.isRefreshing = false
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

    private fun setSearchFunction() =
        editText_SearchText_ManageLaptopActivitySearchMode.addTextChangedListener {

            viewModel.search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))

            var currentSize = viewModel.getShowList().size

            var beforeTime = System.currentTimeMillis()

            if(lastSize != currentSize){
                pageView.syncPage()
                lastSize = currentSize
            }

            var afterTime = System.currentTimeMillis()

            Log.d("TestLog", "syncTime = ${(afterTime - beforeTime)}")
        }



    private fun setTitleBarSearchMode() {
        isViewMode = false
        titleBar_ViewMode_ManageLaptopActivity.visibility = View.GONE
        titleBar_SearchMode_ManageLaptopActivity.visibility = View.VISIBLE
    }

    private fun setTitleBarViewMode() {
        isViewMode = true
        titleBar_ViewMode_ManageLaptopActivity.visibility = View.VISIBLE
        titleBar_SearchMode_ManageLaptopActivity.visibility = View.GONE
        viewModel.search("")
        pageView.syncPage()
    }

    //region 뒤로가기 버튼 눌렀을 때
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
    //endregion

    //region 앱 완전 종료
    private fun finishApp(){
        moveTaskToBack(true)                        // 태스크를 백그라운드로 이동
        finishAndRemoveTask()                        // 액티비티 종료 + 태스크 리스트에서 지우기
        Process.killProcess(Process.myPid())    // 앱 프로세스 종료
    }
    //endregion
}