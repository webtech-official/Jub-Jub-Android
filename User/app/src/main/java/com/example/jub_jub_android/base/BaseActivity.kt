package com.example.jub_jub_android.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.jub_jub_android.BR

abstract class BaseActivity <B: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes private val layoutResId: Int
    ): AppCompatActivity() {

    var backKeyPressedTime : Long = 0


    lateinit var binding: B
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this

    }

//    override fun onBackPressed() {
//
//        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
//            backKeyPressedTime = System.currentTimeMillis()
//            showToast("'뒤로' 버튼을 한번 더 누르시면 종료됩니다.")
//        }
//        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
//            finish()
//        }
//    }

    fun showToast(message: String) = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    fun showLog(msg: String) = Log.d("TestLog", msg)
}