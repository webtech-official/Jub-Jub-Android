package com.example.jub_jub_android.extension

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.jub_jub_android.R
import com.example.jub_jub_android.util.MyUtil
import java.util.*


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


@BindingAdapter("equipment_image")
fun setImage(view: View, image: String){
    (view as ImageView).setImageBitmap(MyUtil.convertBase64ToBitmap(image))
}

@BindingAdapter("equipment_count")
fun setCount(view: View, count: Int){
    (view as TextView).text = "수량 : ${count}개"
}

@BindingAdapter("rent_amount")
fun setAmount(view: View, count: Int){
    (view as TextView).text = "$count"
}

@BindingAdapter("rent_status")
fun setStatus(view: View, status: String) {
    (view as TextView).text = MyUtil.getRentStatus(status)
}

