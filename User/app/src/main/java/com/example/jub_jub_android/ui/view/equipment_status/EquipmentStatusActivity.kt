package com.example.jub_jub_android.ui.view.equipment_status

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.Toast
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityMainBinding
import com.example.jub_jub_android.ui.view.mypage.MyPageActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EquipmentStatusActivity : BaseActivity<ActivityMainBinding, EquipmentStatus_ViewModel>(R.layout.activity_main) {

    override val viewModel: EquipmentStatus_ViewModel by viewModel()

    var isViewMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {

        binding.activity = this

        viewModel.init(applicationContext)

        viewModel.initPageView(this, pageView_MainActivity)

        refreshLayout.setOnRefreshListener {
            refresh()
        }

        EquipmentStatus_ViewModel.list.observe(this, {
            viewModel.pageView.syncPage()
        })

        viewModel.searchText.observe(this, {
            viewModel.search(it.toString().toLowerCase(Locale.getDefault()).replace(" ", ""))
        })

    }

    //region 새로고침
    private fun refresh(){
        refreshLayout.isRefreshing = true
        viewModel.getEquipmentData(applicationContext)
        refreshLayout.isRefreshing = false
        Toast.makeText(applicationContext, "새로고침 완료", Toast.LENGTH_SHORT).show()
    }
    //endregion

    fun myPage() {
        startActivity(Intent(applicationContext, MyPageActivity::class.java))
    }

    fun setTitleBarSearchMode() {
        isViewMode = false
    }

    fun setTitleBarViewMode() {
        isViewMode = true
        viewModel.searchText.value = ""
    }

    fun getIsViewMode(): Boolean {
        return isViewMode
    }

    private fun finishApp(){
        moveTaskToBack(true)                        // 태스크를 백그라운드로 이동
        finishAndRemoveTask()                        // 액티비티 종료 + 태스크 리스트에서 지우기
        Process.killProcess(Process.myPid())    // 앱 프로세스 종료
    }

}