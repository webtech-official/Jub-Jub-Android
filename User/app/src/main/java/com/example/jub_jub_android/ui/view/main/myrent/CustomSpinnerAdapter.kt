package com.example.jub_jub_android.ui.view.main.myrent

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.jub_jub_android.R

class CustomSpinnerAdapter(private val con: Context, private val itemLayoutId: Int, private val dataList: Array<String>) : ArrayAdapter<String>(con, itemLayoutId, dataList){

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): String? {
        return dataList[position]
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v = LayoutInflater.from(con).inflate(R.layout.layout_rent_status_current_item, parent, false)

        v.setBackgroundResource(R.drawable.bg_spinner)
        v.foreground = null
        Log.d("TestLog", "$v")
        //v.findViewById<TextView>(R.id.textView_RentStatus_Item).text = dataList[position]
                //super.getView(position, convertView, parent)

        return v
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(con).inflate(itemLayoutId, parent,false)
        view.findViewById<TextView>(R.id.textView_RentStatus_Item).text = dataList[position]

        if(position == 0){
            view.background = ContextCompat.getDrawable(context, R.drawable.bg_top_spinner_item)
            view.foreground = null
        }else if (position == dataList.size){
            view.background = ContextCompat.getDrawable(context, R.drawable.bg_bottom_spinner_item)
            view.foreground = null
        }
        Log.d("TestLog", "$view")
        return view
    }
}