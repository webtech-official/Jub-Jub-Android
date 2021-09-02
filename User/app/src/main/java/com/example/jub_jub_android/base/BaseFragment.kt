package com.example.jub_jub_android.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.room.util.DBUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jub_jub_android.BR

abstract class BaseFragment<B: ViewDataBinding, VM: ViewModel>(
    @LayoutRes private val layoutResId: Int
): Fragment() {

    lateinit var binding: B

    abstract val viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
    }


    fun refreshEnd(it: SwipeRefreshLayout){
        showToast("새로고침 완료!")
        it.isRefreshing = false
    }

    fun showToast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLog(msg: String){
        Log.d("TestLog", msg)
    }
}