package com.example.jub_jub_admin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.body.SignUp
import com.example.jub_jub_admin.entity.dataclass.response.SignUpResponse
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

                val signUpData = SignUp(
                    editText_ClassNum_SignUpActivity.text.toString(),
                    editText_Email_SignUpActivity.text.toString(),

                    editText_Name_SignUpActivity.text.toString(),
                    editText_Password_SignUpActivity.text.toString()
                )

                val response: Call<SignUpResponse> = NetRetrofit.getServiceApi().signUp(signUpData)

                response.enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        Log.d("TestLog", "onResponse!")
                        if (response.code() == 200) {
                            Log.d("TestLog", "success = ${response.body()?.success}")
                            Toast.makeText(applicationContext, "회원가입 완료!", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Log.d("TestLog", "${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        Log.d("TestLog", "onFailure! ${t.message}")
                    }
                })
            }

        }

    }
    private fun checkEditText(): Boolean{

        return if(editText_ClassNum_SignUpActivity.text.toString() == "" || editText_Email_SignUpActivity.text.toString() == "" || editText_Name_SignUpActivity.text.toString() == ""
            || editText_Password_SignUpActivity.text.toString() == "" || editText_PasswordCheck_SignUpActivity.text.toString() == "" ) {
            Toast.makeText(applicationContext, "빈칸을 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
            false
        } else if(editText_Password_SignUpActivity.text.toString() != editText_PasswordCheck_SignUpActivity.text.toString()){
            Toast.makeText(applicationContext, "Password와 Password check의 값이 달라요! ", Toast.LENGTH_SHORT).show()
            false
        } else{
            Log.d("TestLog", "checkEditText 통과")
            true
        }

    }


}