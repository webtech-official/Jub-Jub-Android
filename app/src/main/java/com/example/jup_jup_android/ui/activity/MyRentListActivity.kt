package com.example.jup_jup_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.RentStatus
import com.example.jup_jup_android.entity.singleton.RentStatusListManager
import com.example.jup_jup_android.ui.util.MyRentList_PageView
import kotlinx.android.synthetic.main.activity_my_rental_list.*
import kotlinx.android.synthetic.main.layout_pageview.*

class MyRentListActivity : AppCompatActivity() {

    lateinit var pageView : MyRentList_PageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_rental_list)

        Log.d("TestLog", "showList.size = ${RentStatusListManager.getShowList().size}")
        pageView = MyRentList_PageView(applicationContext, pageView_MyRentalListActivity, RentStatusListManager.getShowList())
        pageView.initViewPager()

        setTitleBarItemsOnclick()

        refreshLayout.setOnRefreshListener {

            RentStatusListManager.addRentStatusItem(RentStatus("1","DC모터", "모터", 100, "","반납"))
            RentStatusListManager.addRentStatusItem(RentStatus("1","DC모터", "모터", 100, "","대여"))
            RentStatusListManager.addRentStatusItem(RentStatus("1","DC모터", "모터", 100, "","연체"))
            pageView.syncPage()
        }
    }


    private fun setTitleBarItemsOnclick() {

        imageView_BackArrow_MyRentalListActivity.setOnClickListener {
            back()
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

            //RentAdapter.getViewPagerAdapter().notifyDataSetChanged()
            //RentAdapter.getRecyclerAdapter().notifyDataSetChanged()
            pageView.syncPage()

            Log.d("TestLog", "after click size = ${RentStatusListManager.getShowList().size}")
        }
    }
    private fun setShowModeText(text: String){
        textView_ShowMode_MyRentalListActivity.text = text
    }

    private fun back(){
        RentStatusListManager.showOriginalDividedList()
        pageView.syncPage()
        finish()
    }

    override fun onBackPressed() {
        back()
    }
}