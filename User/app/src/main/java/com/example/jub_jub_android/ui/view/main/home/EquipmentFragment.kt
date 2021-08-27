package com.example.jub_jub_android.ui.view.main.home

import android.os.Bundle
import android.view.View
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseFragment
import com.example.jub_jub_android.databinding.FragmentEquipmentBinding
import com.example.jub_jub_android.ui.view.equipment_status.EquipmentStatus_ViewModel
import com.example.jub_jub_android.ui.view.main.EquipmentRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EquipmentFragment() : BaseFragment<FragmentEquipmentBinding, EquipmentStatus_ViewModel>(R.layout.fragment_equipment) {

    override val viewModel: EquipmentStatus_ViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EquipmentRecyclerViewAdapter(arrayListOf(0,1,2,3,4,5,6,7,8,9,10))

        binding.recyclerViewEquipmentList.adapter = adapter
        adapter.notifyDataSetChanged()


    }
}