package com.example.jub_jub_admin.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.body.Login
import com.example.jub_jub_admin.entity.dataclass.response.LoginResponse
import com.example.jub_jub_admin.entity.singleton.TokenManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    //마지막으로 뒤로가기 버튼 누른 시간
    var backKeyPressedTime : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setPermission()


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
                            if(response.isSuccessful){

                                if(response.body()?.success == true){

                                    Log.d("TestLog_Login", "로그인 성공!")
                                    Log.d("TestLog_Login", "code = ${response.body()?.code}" +
                                            "data = ${response.body()?.data} msg = ${response.body()?.msg} success = ${response.body()?.success} ")
                                    TokenManager.setToken(response.body()?.data!!)
                                    //앱 시작

                                    Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(applicationContext, MainActivity::class.java))
                                    finish()
                                }
                                else{
                                    Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                                }

                            }
                            else{
                                Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })

                }
            }

        }
    }

    private fun setPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "READ 이미 거절 됨",  Toast.LENGTH_SHORT).show()
                Log.d("TestLog_Login", "READ 이미 거절 됨")
            }else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
                Log.d("TestLog_Login", "READ 허용 요청")
            }
        }else{
            Toast.makeText(this, "READ 이미 승인 됨",  Toast.LENGTH_SHORT).show()
            Log.d("TestLog_Login", "READ 이미 승인 됨")
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "WRITE 이미 거절 됨",  Toast.LENGTH_SHORT).show()
                Log.d("TestLog_Login", "WRITE 이미 거절 됨")
            }else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
                Log.d("TestLog_Login", "WRITE 권한 요청")
            }
        }else{
            Toast.makeText(this, "WRITE 이미 승인 됨",  Toast.LENGTH_SHORT).show()
            Log.d("TestLog_Login", "WRITE 이미 승인 됨")
        }

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