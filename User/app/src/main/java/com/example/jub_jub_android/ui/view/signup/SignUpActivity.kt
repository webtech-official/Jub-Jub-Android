package com.example.jub_jub_android.ui.view.signup

import android.os.Bundle
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivitySignupBinding
import com.example.jub_jub_android.ui.adapter.viewpager.SignUpViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignupBinding, SignUpViewModel>(R.layout.activity_signup) {

    override val viewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initETArray()

        viewModel.adapter = SignUpViewPagerAdapter(viewModel)

        viewModel.adapter.also {
            binding.viewPager2SignUpActivity.adapter = it
            binding.viewPager2SignUpActivity.isUserInputEnabled = false
        }

        viewModel.viewpagerPosition.observe(this, { binding.viewPager2SignUpActivity.currentItem = it })

        viewModel.finish.observe(this, { finish() })

        viewModel.signUpResult.observe(this, {
            showToast(it.msg)
            if(it.success){
                finish()
            }
        })

    }

    private fun initETArray() { for(i in 0..3){ viewModel.signUpEditTextArrayList.add(binding.editText) } }












    private fun checkEditText(): Boolean{
        return true
        /*
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
        }*/
    }

    override fun onBackPressed() {
        viewModel.viewPagerPrevPage()
    }
}