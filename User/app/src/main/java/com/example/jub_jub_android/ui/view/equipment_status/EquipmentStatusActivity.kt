package com.example.jub_jub_android.ui.view.equipment_status

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_android.R
import com.example.jub_jub_android.databinding.ActivityMainBinding
import com.example.jub_jub_android.ui.activity.MyPageActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.util.*


class EquipmentStatusActivity : AppCompatActivity() {

    private lateinit var pageView: EquipmentStatus_PageView

    //마지막으로 뒤로가기 버튼 누른 시간
    private var backKeyPressedTime : Long = 0

    var isViewMode = true

    var searchText = MutableLiveData<String>()

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: EquipmentStatus_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        searchText.value = "android"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.activity = this
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(EquipmentStatus_ViewModel::class.java)
        viewModel.init(applicationContext)

        pageView = EquipmentStatus_PageView(this, pageView_MainActivity, viewModel)

        refreshLayout.setOnRefreshListener {
            refresh()
        }

        EquipmentStatus_ViewModel.i.observe(this, {
            viewModel.getEquipmentData(applicationContext)
        })

        EquipmentStatus_ViewModel.list.observe(this, {
            pageView.syncPage()
        })

        this.searchText.observe(this, {
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
        searchText.value = ""
    }

    fun getIsViewMode(): Boolean {
        return isViewMode
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