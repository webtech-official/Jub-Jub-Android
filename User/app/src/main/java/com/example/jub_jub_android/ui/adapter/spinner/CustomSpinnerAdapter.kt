package com.example.jub_jub_android.ui.adapter.spinner

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filterable
import android.widget.TextView
import com.example.jub_jub_android.R

class CustomSpinnerAdapter(private val con: Context, private val itemLayoutId: Int, private val dataList: Array<String>) : ArrayAdapter<String>(con, itemLayoutId, dataList), Filterable {

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
        v.findViewById<TextView>(R.id.textView_Current_RentStatus).text = dataList[position]

        return v
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(con).inflate(itemLayoutId, parent,false)

        view.findViewById<TextView>(R.id.textView_RentStatus_Item).text = dataList[position]

        return view
    }


    fun showLog(msg: String){
        Log.d("TestLog_SpinnerAdapter", msg)
    }
}