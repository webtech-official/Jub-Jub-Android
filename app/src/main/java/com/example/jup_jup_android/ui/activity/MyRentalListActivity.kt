package com.example.jup_jup_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.RentAdapter
import com.example.jup_jup_android.entity.singleton.RentStatusListManager
import com.example.jup_jup_android.ui.util.SetMyRentList_PageView
import kotlinx.android.synthetic.main.activity_my_rental_list.*

class MyRentalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_rental_list)
        Log.d("TestLog", "showList.size = ${RentStatusListManager.getShowedList().size}")
        SetMyRentList_PageView(applicationContext, pageView_MyRentalListActivity).initViewPager()

        setTitleBarItemsOnclick()
    }


    private fun setTitleBarItemsOnclick() {

        imageView_BackArrow_MyRentalListActivity.setOnClickListener {
            finish()
        }

        textView_ShowMode_MyRentalListActivity.setOnClickListener {
            when(textView_ShowMode_MyRentalListActivity.text.toString()){

                "전체" -> {
                    setShowModeText("반납")
                    RentStatusListManager.showReturnedDividedList()

                }

                "반납" -> {
                    setShowModeText("대여")
                    RentStatusListManager.showRentingDividedList()
                }

                "대여" -> {
                    setShowModeText("연체")
                    RentStatusListManager.showOverDueDividedList()
                }

                "연체" -> {
                    setShowModeText("전체")
                    RentStatusListManager.showOriginalDividedList()
                }

            }

            RentAdapter.getViewPagerAdapter().notifyDataSetChanged()
            //RentAdapter.getRecyclerAdapter().notifyDataSetChanged()

            Log.d("TestLog", "after click size = ${RentStatusListManager.dividedShowList.size}")
        }
    }
    private fun setShowModeText(text: String){
        textView_ShowMode_MyRentalListActivity.text = text
    }

    override fun onBackPressed() {

    }
}