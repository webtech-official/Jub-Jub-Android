package com.example.jup_jup_android.ui.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import com.example.jup_jup_android.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pageview.view.*

class SetPageView(context: Context, view: View) {
    var context = context
    var view = view
    private val NEXT_PAGE = +1
    private val PREV_PAGE = -1
    private val THIS_PAGE = 0
    var lastPage = 0
    private lateinit var textViewArrayList: ArrayList<TextView>
    private lateinit var viewPager : ViewPager


    fun initViewPager() {

        viewPager = view.viewPager
        textViewArrayList = arrayListOf<TextView>(view.findViewById(R.id.textView_PageNum1), view.findViewById(R.id.textView_PageNum2),
                view.findViewById(R.id.textView_PageNum3), view.findViewById(R.id.textView_PageNum4), view.findViewById(R.id.textView_PageNum5))

        var viewPagerAdpater = ViewPagerAdapter(context)
        viewPager.adapter = viewPagerAdpater
        viewPagerAdpater.notifyDataSetChanged()

        setBottomPageButtonsOnclick()

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(arg0: Int) {
                Log.d("TestLog", "onPageSelected arg0 = $arg0")
                changePageByViewPagerSwipe(arg0)

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })



    }

    private fun setBottomPageButtonsOnclick() {

        //이전 페이지 이미지 버튼
        view.imageView_PrevPage.setOnClickListener {
            if (viewPager.currentItem != 0) {
                changePageByButton(viewPager.currentItem + PREV_PAGE)
            }
        }

        //다음 페이지 이미지 버튼
        view.imageView_NextPage.setOnClickListener {
            if (viewPager.currentItem != ItemStatusListManager.getDevidedItemStatusList().size - 1) {
                changePageByButton(viewPager.currentItem + NEXT_PAGE)
            }
        }

        //Page 넘버 클릭해서 이동
        for (i in 0..4) {
            textViewArrayList[i].setOnClickListener {
                changePageByButton(textViewArrayList[i].text.toString().toInt() - 1)
            }
        }

    }

    private fun changePageNumsText(direction: Int) {
        for (i in 0 until 5) {
            textViewArrayList[i].text = "${textViewArrayList[i].text.toString().toInt() + 5 * direction}"
        }
    }

    private fun checkFragmentBlankPageNum() {
        var maxPage = ItemStatusListManager.getDevidedItemStatusList().size
        for (i in 0 until 5) {
            if (textViewArrayList[i].text.toString().toInt() > maxPage) {
                textViewArrayList[i].visibility = View.GONE
            } else {
                textViewArrayList[i].visibility = View.VISIBLE
            }
        }

    }

    fun changePageByViewPagerSwipe(destination: Int) {
        textHighlightOn(destination)

        // 4 -> 5
        if (lastPage % 5 == 4 && destination % 5 == 0) {
            changePageNumsText(NEXT_PAGE)
        }
        // 5 -> 4
        else if (lastPage % 5 == 0 && destination % 5 == 4) {
            changePageNumsText(PREV_PAGE)
        }
        textHighlightOff()
        checkFragmentBlankPageNum()

    }

    fun changePageByButton(destination: Int) {

        //다음 페이지
        textHighlightOff()
        textHighlightOn(destination)


        viewPager.currentItem = destination
        //lastPage = destination
    }

    fun textHighlightOff() {
        // textViewArrayList[viewPager_ItemStatusList.currentItem % 5].text.toString().toInt()
        textViewArrayList[lastPage % 5].textSize = 18f
        textViewArrayList[lastPage % 5].setShadowLayer(0f, 0f, 0f, Color.parseColor("#FFFFFF"))
        lastPage = viewPager.currentItem
    }

    fun textHighlightOn(destination: Int) {
        //var destination = textViewArrayList[dest % 5].text.toString().toInt()
        textViewArrayList[destination % 5].textSize = 25f
        textViewArrayList[destination % 5].setShadowLayer(20f, 0f, 0f, Color.parseColor("#FFFFFF"))
    }



}

