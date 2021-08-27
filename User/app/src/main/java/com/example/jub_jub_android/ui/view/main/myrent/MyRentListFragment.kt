package com.example.jub_jub_android.ui.view.main.myrent

import android.os.Bundle
import android.view.View
import com.example.jub_jub_android.R
import com.example.jub_jub_android.base.BaseFragment
import com.example.jub_jub_android.databinding.FragmentMyRentListBinding
import com.example.jub_jub_android.ui.view.myrentstatus.MyRentList_ViewModel
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyRentListFragment: BaseFragment<FragmentMyRentListBinding, MyRentList_ViewModel>(R.layout.fragment_my_rent_list) {

    override val viewModel: MyRentList_ViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RentListRecyclerViewAdapter(arrayListOf(0,1,2,3,4))

        binding.recyclerViewRentList.adapter = adapter
        adapter.notifyDataSetChanged()

        val spinnerAdapter = CustomSpinnerAdapter(requireContext(), R.layout.layout_rent_status_item, resources.getStringArray(R.array.spinner_array))

        binding.spinnerRentStatus.adapter = spinnerAdapter

    }


}