package com.example.jub_jub_android.ui.view.main.myrent

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseFragment
import com.example.jub_jub_android.databinding.FragmentMyRentListBinding
import com.example.jub_jub_android.ui.adapter.spinner.CustomSpinnerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyRentListFragment: BaseFragment<FragmentMyRentListBinding, MyRentViewModel>(R.layout.fragment_my_rent_list) {

    override val viewModel: MyRentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerViewRentList.adapter = viewModel.adapter

        viewModel.showStatus.observe(viewLifecycleOwner, {
            viewModel.adapter.filter.filter(it)
        })

        val spinnerAdapter = CustomSpinnerAdapter(requireContext(), R.layout.layout_rent_status_spinner_item_bottom, resources.getStringArray(R.array.spinner_array))

        binding.spinnerRentStatus.adapter = spinnerAdapter

        binding.spinnerRentStatus.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.adapter.filter.filter(binding.spinnerRentStatus.selectedItem.toString())
                showLog("Spinner_Item = ${binding.spinnerRentStatus.selectedItem.toString()}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

}