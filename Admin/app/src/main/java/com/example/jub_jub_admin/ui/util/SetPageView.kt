package com.example.jub_jub_admin.ui.util

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.jub_jub_admin.R
import kotlinx.android.synthetic.main.layout_pageview.view.*

class SetPageView(val view: View, private val viewPager: ViewPager, private val dataList: ArrayList<ArrayList<Any>>) {

    private val NEXT_PAGE = +1
    private val PREV_PAGE = -1
    var lastPage = 0

    private lateinit var textViewArrayList : ArrayList<TextView>


    init {

        setTextViewArrayList(view)
        viewPager.adapter?.notifyDataSetChanged()

        setBottomPageButtonsOnclick()

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            //Page가 바뀌면 실행되는 함수
            override fun onPageSelected(arg0: Int) {
                changePageByViewPagerSwipe(arg0)
                Log.d("TestLog", "dataList.size = ${dataList.size}")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })

        checkFragmentBlankPageNum(dataList.size)
    }


    private fun setTextViewArrayList(view: View){
        textViewArrayList = arrayListOf<TextView>(view.findViewById(R.id.textView_PageNum1), view.findViewById(R.id.textView_PageNum2),
            view.findViewById(R.id.textView_PageNum3), view.findViewById(R.id.textView_PageNum4), view.findViewById(
                R.id.textView_PageNum5))
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
            if (viewPager.currentItem != dataList.size - 1) {
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

    fun changePageByViewPagerSwipe(destination: Int) {
        // 14 -> 15

        // lastPage == destination-1은 6에서10을 / 10에서 6을 클릭하면 bottomPage가 넘어가지않는데 숫자가 update되는 오류가 있어서 추가함
        if (lastPage % 5 == 4 && destination % 5 == 0 && lastPage == destination-1) {
            changePageNumsText(NEXT_PAGE)
        }
        // 5 -> 4
        else if (lastPage % 5 == 0 && destination % 5 == 4 && lastPage == destination+1 ) {
            changePageNumsText(PREV_PAGE)
        }

        textHighlightOn(destination)
        textHighlightOff()
        checkFragmentBlankPageNum(dataList.size)
    }

    private fun changePageByButton(destination: Int) {

        //다음 페이지
        textHighlightOff()
        textHighlightOn(destination)
        viewPager.setCurrentItem(destination, true)

    }

    // 존재하지 않는 페이지의 숫자는 GONE으로, 존재하는데 보이지 않는 페이지는 VISIBLE로
    private fun checkFragmentBlankPageNum(maxPage: Int) {
        for (i in 0 until 5) {
            if (textViewArrayList[i].text.toString().toInt() > maxPage) {
                textViewArrayList[i].visibility = View.GONE
            } else {
                textViewArrayList[i].visibility = View.VISIBLE
            }
        }
    }

    private fun textHighlightOff() {
        textViewArrayList[lastPage % 5].textSize = 18f
        textViewArrayList[lastPage % 5].setShadowLayer(0f, 0f, 0f, Color.parseColor("#FFFFFF"))
        lastPage = viewPager.currentItem
    }

    private fun textHighlightOn(destination: Int) {
        textViewArrayList[destination % 5].textSize = 25f
        textViewArrayList[destination % 5].setShadowLayer(20f, 0f, 0f, Color.parseColor("#FFFFFF"))
    }

    private fun changePageNumsText(direction: Int) {
        for (i in 0 until 5) {
            textViewArrayList[i].text = "${textViewArrayList[i].text.toString().toInt() + 5 * direction}"
        }
    }

    private fun setPageNumsText(startPageNum: Int){
        for (i in 0 until 5) {
            textViewArrayList[i].text = "${startPageNum + i}"
        }
    }

    fun syncPage(size: Int){
        viewPager.adapter?.notifyDataSetChanged()
        lastPage = viewPager.currentItem
        if(textViewArrayList[0].text.toString().toInt() > size){
            setPageNumsText(size - size%5 + 1)
        }

        changePageByButton(viewPager.currentItem)
        checkFragmentBlankPageNum(size)
    }
}