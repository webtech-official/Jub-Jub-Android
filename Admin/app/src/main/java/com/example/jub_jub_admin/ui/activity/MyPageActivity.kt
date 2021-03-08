package com.example.jub_jub_admin.ui.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.local.SharedPref
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.AllowRentRequest.AllowRentRequest_Activity
import com.example.jub_jub_admin.ui.ManageLaptop.ManageLaptop_Activity
import com.example.jub_jub_admin.ui.ModifyLaptop.ModifyLaptop_Activity
import com.example.jub_jub_admin.ui.manageEquipment.ManageEquipment_Activity
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        imageView_BackArrow_MyPage.setOnClickListener {
            finish()
        }

        //기자재 관리
        textView_ManageItem_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ManageEquipment_Activity::class.java))
            finishAffinity()
        }

        //기자재 추가
        textView_AddItem_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ModifyEquipmentActivity::class.java))
        }

        //노트북 관리
        textView_ManageLaptop_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ManageLaptop_Activity::class.java))
            finishAffinity()
        }

        //노트북 추가
        textView_AddLaptop_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ModifyLaptop_Activity::class.java))
        }

        //노트북 사양 추가
        textView_AddLaptopSpec_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, AddLaptopSpecActivity::class.java))
        }

        //기자재 대여 승인
        textView_AcceptRentItem_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, AllowRentRequest_Activity::class.java))
            finishAffinity()
        }
        //학생 관리
        textView_ManageStudent_MyPage.setOnClickListener {
            Toast.makeText(applicationContext, "개발중인 기능입니다!", Toast.LENGTH_SHORT).show()
        }

        //대여 기록
        textView_RentRecord_MyPage.setOnClickListener {
            Toast.makeText(applicationContext, "개발중인 기능입니다!", Toast.LENGTH_SHORT).show()
        }

        textView_Logout_MyPage.setOnClickListener {
            logOut()

        }
    }

    private fun logOut() {
        //Toast.makeText(applicationContext, "정말 로그아웃 하시겠습니까?", Toast.LENGTH_SHORT).show()
        TokenManager.removeToken()
        SharedPref(applicationContext).logOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(FLAG_ACTIVITY_SINGLE_TOP))
        //Activity Stack 아래에 있는 Activity들도 모두 종료
        finishAffinity()
    }
}