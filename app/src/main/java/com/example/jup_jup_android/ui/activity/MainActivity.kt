package com.example.jup_jup_android.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import com.example.jup_jup_android.ui.adapter.ItemStatusList_RecyclerViewAdpater
import com.example.jup_jup_android.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val NEXT_PAGE = +1
    private val PREV_PAGE = -1
    var lastPage = 0
    private lateinit var textViewArrayList : ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewArrayList = arrayListOf<TextView>(findViewById(R.id.textView_PageNum1_MainActivity), findViewById(R.id.textView_PageNum2_MainActivity)
                , findViewById(R.id.textView_PageNum3_MainActivity),findViewById(R.id.textView_PageNum4_MainActivity),findViewById(R.id.textView_PageNum5_MainActivity))

        var viewPagerAdpater = ViewPagerAdapter(applicationContext)
        viewPager_ItemStatusList.adapter = viewPagerAdpater
        viewPagerAdpater.notifyDataSetChanged()
        Log.d("TestLog", "ViewPagerAdapter 설정 완료")

        viewPager_ItemStatusList.setOnPageChangeListener(){

        }


        imageView_PrevPage_MainActivity.setOnClickListener {
            if(viewPager_ItemStatusList.currentItem != 0){
                movePage(PREV_PAGE)
            }
        }

        imageView_NextPage_MainActivity.setOnClickListener {
            Log.d("TestLog", "${viewPager_ItemStatusList.currentItem}")
            if(viewPager_ItemStatusList.currentItem != ItemStatusListManager.getDevidedItemStatusList().size-1){
                movePage(NEXT_PAGE)
            }

        }
    }

    fun movePage(change: Int){
        //다음 페이지
        viewPager_ItemStatusList.currentItem = viewPager_ItemStatusList.currentItem + change
        textHighlightOff()
        textHighlightOn(change)
        lastPage += change
    }

    fun textHighlightOff(){
        textViewArrayList[lastPage].textSize = 18f
        textViewArrayList[lastPage].setShadowLayer(0f, 0f, 0f, Color.parseColor("#FFFFFF"))
    }

    fun textHighlightOn(change: Int){
        lastPage += change
        textViewArrayList[lastPage].textSize = 25f
        textViewArrayList[lastPage].setShadowLayer(20f, 0f, 0f, Color.parseColor("#FFFFFF"))
    }

}

private fun ViewPager.setOnPageChangeListener(function: () -> Unit) {
    currentItem

}
