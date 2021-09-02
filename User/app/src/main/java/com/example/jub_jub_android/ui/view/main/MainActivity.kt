package com.example.jub_jub_android.ui.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseActivity
import com.example.jub_jub_android.databinding.ActivityMainBinding
import com.example.jub_jub_android.ui.view.main.equipment.EquipmentFragment
import com.example.jub_jub_android.ui.view.main.mypage.MyPageFragment
import com.example.jub_jub_android.ui.view.main.myrent.MyRentListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MViewModel>(R.layout.activity_main) {

    var backKeyPressedTime : Long = 0

    override val viewModel: MViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initBottomNav()
    }

    private fun initBottomNav() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){

                R.id.menu_Equipment -> { replaceFragment(EquipmentFragment()) }

                R.id.menu_RentList -> { replaceFragment(MyRentListFragment()) }

                R.id.menu_MyPage -> { replaceFragment(MyPageFragment()) }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }

    }


    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()

        return true
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