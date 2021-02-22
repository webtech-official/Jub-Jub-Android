package com.example.jub_jub_android.ui.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.SharedPref
import com.example.jub_jub_android.entity.singleton.TokenManager
import com.example.jub_jub_android.ui.util.MyUtil
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.layout_alertdialog.*

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
            val dialog = MyUtil.makeBaseDialog(this, "로그아웃")

            dialog.textView_Cancel_AlertDialogLayout.setOnClickListener {
                dialog.dismiss()
            }
            dialog.textView_Accept_AlertDialogLayout.setOnClickListener {
                logOut()
                dialog.dismiss()
            }

            dialog.show()
        }
    }
  
    private fun logOut(){
        TokenManager.removeToken()
        SharedPref(applicationContext).logOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java).addFlags(FLAG_ACTIVITY_SINGLE_TOP))
        //Activity Stack 아래에 있는 Activity들도 모두 종료
        finishAffinity()
    }
}