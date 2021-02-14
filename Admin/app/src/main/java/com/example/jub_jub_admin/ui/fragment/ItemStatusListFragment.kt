package com.example.jub_jub_admin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.entity.dataclass.Equipment

class ItemStatusListFragment(itemStatusList: ArrayList<Equipment>) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_item_status_list, container, false)

        return view
    }

}