package com.example.jub_jub_android.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.SharedPref
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.entity.dataclass.response.LoginResponse
import com.example.jub_jub_android.entity.singleton.TokenManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    //마지막으로 뒤로가기 버튼 누른 시간
    var backKeyPressedTime : Long = 0
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        autoLogin()

        textView_GotoRegister_LoginActivity.setOnClickListener {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }

        button_Login_LoginActivity.setOnClickListener {
            if(checkEditText()){
                login(Login(editText_Email_LoginActivity.text.toString(), editText_Password_LoginActivity.text.toString()))
            }
        }

    }

    private fun autoLogin() {
        if(intent.hasExtra("LoginData")){
            login(intent.getSerializableExtra("LoginData") as Login)
        }
    }

    fun checkEditText(): Boolean {
        return if(editText_Email_LoginActivity.text.toString() == "") {
            Toast.makeText(applicationContext, "Email을 입력해주세요!", Toast.LENGTH_SHORT).show()
            false
        } else {
            if(editText_Password_LoginActivity.text.toString() == "") {
                Toast.makeText(applicationContext, "Password를 입력해주세요!", Toast.LENGTH_SHORT).show()
                false
            } else {
                true
            }
        }
    }

    fun login(loginData: Login){

        progress_bar.visibility = View.VISIBLE

        val response: Call<LoginResponse> = NetRetrofit.getServiceApi().login(loginData)

        response.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
            {
                progress_bar.visibility = View.GONE
                if(response.isSuccessful){
                    if(response.body()?.success == true){
                        Log.d("TestLog", "로그인 성공!")
                        Log.d("TestLog1", "code = ${response.body()?.code}" +
                                "" +
                                "data = ${response.body()?.data?.accessToken} msg = ${response.body()?.msg} success = ${response.body()?.success} ")

                        TokenManager.setToken("${response.body()?.data?.accessToken}")
                        Log.d("TestLog_Login", TokenManager.getToken())
                        sharedPref = SharedPref(applicationContext)
                        sharedPref.saveAccount(loginData.email, loginData.password)
                        //앱 시작

                        Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    } else{
                        Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                        Log.d("TestLog_Login", "${response.body()?.msg}")
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                progress_bar.visibility = View.GONE
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                Log.d("TestLog", "onFailure ${t.message.toString()}")
            }
        })
    }

    override fun onBackPressed() {

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(applicationContext, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishApp()
        }
    }

    private fun finishApp(){
        moveTaskToBack(true)                        // 태스크를 백그라운드로 이동
        finishAndRemoveTask()                        // 액티비티 종료 + 태스크 리스트에서 지우기
        android.os.Process.killProcess(android.os.Process.myPid())    // 앱 프로세스 종료
    }

}