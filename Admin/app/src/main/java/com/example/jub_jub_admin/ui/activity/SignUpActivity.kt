package com.example.jub_jub_admin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.body.SignUp
import com.example.jub_jub_admin.entity.dataclass.response.MyResponse
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        button_SignUp_SignUpActivity.setOnClickListener {
            if (checkEditText()) {
                progress_bar.visibility = View.VISIBLE
                signUp()
            }
        }
    }

    private fun signUp(){
        val signUpData = SignUp(
                editText_ClassNum_SignUpActivity.text.toString(),
                editText_Email_SignUpActivity.text.toString(),
                editText_Name_SignUpActivity.text.toString(),
                editText_Password_SignUpActivity.text.toString()
        )
        val response: Call<MyResponse> = NetRetrofit.getServiceApi().signUp(signUpData)

        response.enqueue(object : Callback<MyResponse> {

            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                progress_bar.visibility = View.GONE
                if (response.body()?.success!!) {
                    Toast.makeText(applicationContext, "계정 생성 완료! 이메일을 확인해주세요!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.d("TestLog", "${response.body()?.msg}")
                    Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "서버 연결 실패", Toast.LENGTH_SHORT).show()
                Log.d("TestLog", "onFailure! ${t.message}")
                progress_bar.visibility = View.GONE
            }
        })
    }


    private fun checkEditText(): Boolean{

        return if(editText_ClassNum_SignUpActivity.text.toString() == "" || editText_Email_SignUpActivity.text.toString() == "" || editText_Name_SignUpActivity.text.toString() == ""
            || editText_Password_SignUpActivity.text.toString() == "" || editText_PasswordCheck_SignUpActivity.text.toString() == "" ) {
            Toast.makeText(applicationContext, "빈칸을 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
            false
        } else if(editText_Password_SignUpActivity.text.toString() != editText_PasswordCheck_SignUpActivity.text.toString()){
            Toast.makeText(applicationContext, "Password와 Password check의 값이 다릅니다", Toast.LENGTH_SHORT).show()
            false
        } else{
            Log.d("TestLog", "checkEditText 통과")
            true
        }

    }


}