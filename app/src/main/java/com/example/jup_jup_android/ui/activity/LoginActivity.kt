package com.example.jup_jup_android.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.jup_jup_android.R
import com.example.jup_jup_android.data.remote.NetRetrofit
import com.example.jup_jup_android.entity.dataclass.body.Login
import com.example.jup_jup_android.entity.dataclass.response.LoginResponse
import com.example.jup_jup_android.entity.singleton.TokenManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textView_GotoRegister_LoginActivity.setOnClickListener {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }

        textView_LogIn_LoginActivity.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        button_Login_LoginActivity.setOnClickListener {
            if(editText_Email_LoginActivity.text.toString() == "") {
                Toast.makeText(applicationContext, "Email을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else {
                if(editText_Password_LoginActivity.text.toString() == "") {
                    Toast.makeText(applicationContext, "Password를 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    val loginData = Login(editText_Email_LoginActivity.text.toString(), editText_Password_LoginActivity.text.toString())

                    val response: Call<LoginResponse> = NetRetrofit.getServiceApi().login(loginData)

                    response.enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
                        {
                            if(response.code() == 200){
                                if(response.body()?.msg != null){
                                    Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()

                                } else{
                                    Log.d("TestLog", "로그인 성공!")
                                    Log.d("TestLog", "email = ${response.body()?.email}" +
                                            "" +
                                            "classNum = ${response.body()?.classNumber} token = ${response.body()?.token} msg = ${response?.body()?.msg} ")
                                    TokenManager.setToken(response.body()?.token!!)
                                    //앱 시작

                                    Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(applicationContext, MainActivity::class.java))
                                    finish()
                                }
                            }
                            else {
                                Log.d("TestLog", "로그인 실패! ${response.code()}")
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.d("TestLog", "onFailure ${t.message.toString()}")
                        }
                    })

                }
            }

        }
    }
}