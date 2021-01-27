package com.example.jub_jub_admin.ui.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jub_jub_admin.R
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        imageView_BackArrow_MyPage.setOnClickListener {
            finish()
        }

        textView_MyRentalList_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, MyRentListActivity::class.java))
        }

        textView_Logout_MyPage.setOnClickListener {
            //Toast.makeText(applicationContext, "정말 로그아웃 하시겠습니까?", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(FLAG_ACTIVITY_SINGLE_TOP))
            //Activity Stack 아래에 있는 Activity들도 모두 종료
            finishAffinity()

        }
    }
}