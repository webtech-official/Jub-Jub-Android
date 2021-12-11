package com.jubjub.user.ui.view.login

import android.content.Intent
import android.os.Bundle
import com.jubjub.user.R
import com.jubjub.user.base.BaseActivity
import com.jubjub.user.databinding.ActivityLoginBinding
import com.jubjub.user.entity.dataclass.body.Login
import com.jubjub.user.ui.view.main.MainActivity
import com.jubjub.user.ui.view.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    var backKeyPressedTime : Long = 0

    //마지막으로 뒤로가기 버튼 누른 시간
    override val viewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this

        //Test용 코드

        binding.loginTitle.setOnClickListener {
            startMainActivity()
        }

        viewModel.loginResult.observe(this, {
            showToast(it.msg)
            if(it.success){
                startMainActivity()
            }
            viewModel.stopRefreshing()
        })

        //autoLogin()
    }


    fun clickSignUpButton(){
        startActivity(Intent(applicationContext, SignUpActivity::class.java))
    }

    fun clickSignInButton(){
        if(checkEditText()){
            //로그인 시도
            viewModel.signIn(createSignInInstance())
        }
    }

    //이전에 로그인한 기록이 SharedPref에 있다면, 자동으로 로그인 시도
    private fun autoLogin() {
        if(intent.hasExtra("LoginData")){
            viewModel.signIn(intent.getSerializableExtra("LoginData") as Login)
        }
    }

    private fun checkEditText(): Boolean {
        return if(editText_Email_LoginActivity.text.toString() == "") {
            showToast("Email을 입력해주세요!")
            false
        }
        else if(editText_Password_LoginActivity.text.toString() == "") {
            showToast("Password를 입력해주세요!")
            false
        }
        else {
            true
        }
    }

    private fun createSignInInstance(): Login{ return with(binding) { Login(editTextEmailLoginActivity.text.toString(), editTextPasswordLoginActivity.text.toString()) } }

    private fun startMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }



    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            showToast("'뒤로' 버튼을 한번 더 누르시면 종료됩니다.")
        }
        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
    }


}