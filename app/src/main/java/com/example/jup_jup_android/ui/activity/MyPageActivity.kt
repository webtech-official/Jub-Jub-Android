package com.example.jup_jup_android.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jup_jup_android.R
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        imageView_BackArrow_MyPage.setOnClickListener {
            finish()
        }

        textView_MyRentalList_MyPage.setOnClickListener {
            startActivity(Intent(applicationContext, MyRentalListActivity::class.java))
        }

        textView_Logout_MyPage.setOnClickListener {
            //Toast.makeText(applicationContext, "정말 로그아웃 하시겠습니까?", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
            finish()
            finish()
        }
    }
}