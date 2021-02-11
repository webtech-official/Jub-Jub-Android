package com.example.jub_jub_user.ui.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jub_jub_user.R
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
            //startActivity(Intent(applicationContext, MyRentListActivity::class.java))
        }

        //기자재 추가
        textView_AddItem_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ModifyItemActivity::class.java))
        }

        //노트북 관리
        textView_ManageLaptop_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ManageLaptopActivity::class.java))
        }

        //노트북 추가
        textView_AddLaptop_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, ModifyLaptopActivity::class.java))
        }
        //기자재 대여 승인
        textView_AcceptRentItem_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, AllowRentRequestActivity::class.java))
        }
        //학생 관리
        textView_ManageStudent_MyPage.setOnClickListener {

        }

        //대여 기록
        textView_RentRecord_MyPage.setOnClickListener {

        }

        textView_Logout_MyPage.setOnClickListener {
            //Toast.makeText(applicationContext, "정말 로그아웃 하시겠습니까?", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(FLAG_ACTIVITY_SINGLE_TOP))
            //Activity Stack 아래에 있는 Activity들도 모두 종료
            finishAffinity()

        }
    }
}