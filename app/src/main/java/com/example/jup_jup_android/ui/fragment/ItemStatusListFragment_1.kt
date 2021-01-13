package com.example.jup_jup_android.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import com.example.jup_jup_android.ui.adapter.ItemStatusList_RecyclerViewAdpater
import kotlinx.android.synthetic.main.fragment_item_status_list_1.*

class ItemStatusListFragment_1(itemStatusList: ArrayList<ItemStatus>) : Fragment() {


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