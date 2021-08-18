package com.example.jub_jub_android.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityLoginBinding
import com.example.jub_jub_android.entity.dataclass.body.Login
import com.example.jub_jub_android.model.local.SharedPref
import com.example.jub_jub_android.ui.view.equipment_status.EquipmentStatusActivity
import com.example.jub_jub_android.ui.view.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    //마지막으로 뒤로가기 버튼 누른 시간
    override val viewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loginResult.observe(this, {
            showToast(it.msg)
            if(it.success){
                startMainActivity()
            }
        })

        //회원가입 버튼 클릭 시
        viewModel.signUpButtonClick.observe(this, {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        })

        //로그인 버튼 클릭 시
        viewModel.loginButtonClick.observe(this, {
            if(checkEditText()){
                //로그인 시도
                viewModel.login(createLoginInstance())
            }
        })

        //autoLogin()
    }

    //이전에 로그인한 기록이 SharedPref에 있다면, 자동으로 로그인 시도
    private fun autoLogin() {
        if(intent.hasExtra("LoginData")){
            viewModel.login(intent.getSerializableExtra("LoginData") as Login)
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

    private fun createLoginInstance(): Login{
        return with(binding) {Login(editTextEmailLoginActivity.text.toString(), editTextPasswordLoginActivity.text.toString())}
    }

    private fun startMainActivity() {
        startActivity(Intent(applicationContext, EquipmentStatusActivity::class.java))
        finish()

    }

}