package com.example.jub_jub_android.ui.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.local.DB.RentStatusDB
import com.example.jub_jub_android.entity.dataclass.StudentRentStatus
import com.example.jub_jub_android.entity.singleton.StudentRentStatusListManager
import com.example.jub_jub_android.ui.util.PageView.StudentRentStatusList_PageView
import kotlinx.android.synthetic.main.activity_my_rental_list.*
import kotlinx.android.synthetic.main.layout_pageview.*
import java.io.ByteArrayOutputStream

class StudentRentStatusListActivity : AppCompatActivity() {

    lateinit var pageView : StudentRentStatusList_PageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_rental_list)

        //Log.d("TestLog", "showList.size = ${RentStatusListManager.getShowList().size}")
        pageView = StudentRentStatusList_PageView(applicationContext, pageView_MyRentalListActivity, StudentRentStatusListManager.getShowList())
        pageView.initViewPager()

        setTitleBarItemsOnclick()

        refreshLayout.setOnRefreshListener {
            addItems()
            refreshLayout.isRefreshing = false
        }
    }

    private fun addItems(){
        val rentStatusDB = RentStatusDB.getInstance(this)!!
        var r = Runnable {
            val byteStream = ByteArrayOutputStream()
            val bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.imageex)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
            val byteArray: ByteArray = byteStream.toByteArray()
            val baseString = Base64.encodeToString(byteArray, Base64.DEFAULT)

            rentStatusDB.rentStatusDAO().insert(StudentRentStatus("1","DC모터", "모터", 1000, baseString,"반납"))
            rentStatusDB.rentStatusDAO().insert(StudentRentStatus("1","DC모터", "모터", 1000, baseString,"대여"))
            rentStatusDB.rentStatusDAO().insert(StudentRentStatus("1","DC모터", "모터", 1000, baseString,"연체"))
            Log.d("TestLog", "추가완료!")
            Log.d("TestLog", "추가완료!")
        }

        val thread = Thread(r)
        thread.start()

        try {
            thread.join()
        } catch (e : InterruptedException){ }


        StudentRentStatusListManager.processShowList("${textView_ShowMode_MyRentalListActivity.text}")
        pageView.syncPage()
    }

    private fun setTitleBarItemsOnclick() {

        imageView_BackArrow_MyRentalListActivity.setOnClickListener {
            back()
        }

        textView_ShowMode_MyRentalListActivity.setOnClickListener {
            when(textView_ShowMode_MyRentalListActivity.text.toString()){
                "전체" -> setShowMode("반납")
                "반납" -> setShowMode("대여")
                "대여" -> setShowMode("연체")
                "연체" -> setShowMode("전체")
            }

            //RentAdapter.getViewPagerAdapter().notifyDataSetChanged()
            //RentAdapter.getRecyclerAdapter().notifyDataSetChanged()
            pageView.syncPage()

            Log.d("TestLog", "after click size = ${StudentRentStatusListManager.getShowList().size}")
        }
    }
    private fun setShowMode(text: String){
        textView_ShowMode_MyRentalListActivity.text = text
        StudentRentStatusListManager.processShowList(text)
    }

    private fun back(){
        StudentRentStatusListManager.processShowList("전체")
        pageView.syncPage()
        finish()
    }

    override fun onBackPressed() {
        back()
    }
}