package com.jubjub.user.ui.view.main.equipment

import android.os.Bundle
import android.view.View
import com.jubjub.user.R
import com.jubjub.user.base.BaseFragment
import com.jubjub.user.databinding.FragmentEquipmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EquipmentFragment() : BaseFragment<FragmentEquipmentBinding, EquipmentViewModel>(R.layout.fragment_equipment) {

    override val viewModel: EquipmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerViewEquipmentList.adapter = viewModel.adapter

        binding.swipeRefreshEquipment.also {
            it.setOnRefreshListener {
                viewModel.updateEquipmentData()
                refreshEnd(it)
            }
        }

        viewModel.equipmentArrayList.observe(viewLifecycleOwner, {
            viewModel.adapter?.notifyDataSetChanged()
        })

        viewModel.searchText.observe(viewLifecycleOwner, {
            viewModel.adapter?.filter?.filter(it)
        })

    }

}