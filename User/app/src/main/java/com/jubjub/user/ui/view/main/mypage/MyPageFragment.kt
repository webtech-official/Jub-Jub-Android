package com.jubjub.user.ui.view.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jubjub.user.R
import com.jubjub.user.databinding.FragmentMypageBinding
import com.jubjub.user.ui.view.login.LoginActivity
import com.jubjub.user.ui.view.notice.NoticeActivity

class MyPageFragment: Fragment() {

    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragment = this
        binding.lifecycleOwner = this

    }

    fun startNoticeActivity(){
        startActivity(Intent(context, NoticeActivity::class.java))
    }

    fun signOut(){
        startActivity(Intent(context, LoginActivity::class.java))
        requireActivity().finish()
    }

}