package com.example.jub_jub_android.extension

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.jub_jub_android.R


@BindingAdapter("refreshing")
fun refreshing(view: ProgressBar, visible: Boolean){
    if(visible){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("customtitle")
fun setTitle(view: View, text: String){
    view.findViewById<TextView>(R.id.textView_editTextTitle_layout).text = text
}

@BindingAdapter("customHint")
fun updateHint(view: View, text: String){
    view.findViewById<EditText>(R.id.editText_layout).hint = text
}


