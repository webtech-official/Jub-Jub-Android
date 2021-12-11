package com.jubjub.user.ui.adapter.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jubjub.user.R
import com.jubjub.user.databinding.LayoutSignup1Binding
import com.jubjub.user.databinding.LayoutSignup2Binding
import com.jubjub.user.databinding.LayoutSignup3Binding
import com.jubjub.user.databinding.LayoutSignup4Binding
import com.jubjub.user.ui.view.signup.SignUpViewModel
import kotlinx.android.synthetic.main.layout_myedittext.view.*

class SignUpViewPagerAdapter(val viewModel: SignUpViewModel): RecyclerView.Adapter<SignUpViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = when(viewType){
            0 -> inflate(parent, R.layout.layout_signup1)
            1 -> inflate(parent, R.layout.layout_signup2)
            2 -> inflate(parent, R.layout.layout_signup3)
            3 -> inflate(parent, R.layout.layout_signup4)
            else -> inflate(parent, R.layout.layout_signup1)
        }
        return ViewHolder(v, viewModel)
    }

    private fun inflate(parent: ViewGroup, layout: Int): View {
        //val binding = DataBindingUtil.inflate<LayoutSignup1Binding>(LayoutInflater.from(parent.context), layout, parent, false)
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItemStatusListItems()
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(private val view: View, private val viewModel: SignUpViewModel): RecyclerView.ViewHolder(view) {

        fun bindItemStatusListItems() {
            when(itemViewType){
                0 -> signUp1()
                1 -> signUp2()
                2 -> signUp3()
                3 -> signUp4()
            }
        }

        private fun signUp1() {
            val binding = DataBindingUtil.bind<LayoutSignup1Binding>(view)!!
            binding.vm = viewModel
            binding.invalidateAll()

            viewModel.signUpEditTextArrayList[0] = binding.customEditEmail.editText_layout
        }

        private fun signUp2() {
            val binding = DataBindingUtil.bind<LayoutSignup2Binding>(view)!!
            binding.vm = viewModel
            binding.invalidateAll()
            viewModel.signUpEditTextArrayList[1] = binding.customEditPassword.findViewById(R.id.editText_layout)
        }

        private fun signUp3() {
            val binding = DataBindingUtil.bind<LayoutSignup3Binding>(view)!!
            binding.vm = viewModel
            binding.invalidateAll()
            viewModel.signUpEditTextArrayList[2] = binding.customEditName.findViewById(R.id.editText_layout)
        }

        private fun signUp4() {
            val binding = DataBindingUtil.bind<LayoutSignup4Binding>(view)!!
            binding.vm = viewModel
            binding.invalidateAll()
            viewModel.signUpEditTextArrayList[3] = binding.customEditStudentNumber.findViewById(R.id.editText_layout)
            binding.buttonSignUp.text = "회원가입 완료"
        }



    }

}