package com.example.jub_jub_android.extension

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jub_jub_android.di.modules.activityModule


@BindingAdapter("app:refreshing")
fun refreshing(view: ProgressBar, visible: Boolean){
    if(visible){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}
