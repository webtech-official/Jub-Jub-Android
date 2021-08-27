package com.example.jub_jub_android.ui.view.rent_request

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jub_jub_android.R
import com.example.jub_jub_android.databinding.ActivityRentBinding
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.entity.dataclass.response.EquipmentAllowDTO
import com.example.jub_jub_android.util.MyUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_rent.*
import kotlinx.android.synthetic.main.layout_alertdialog.*


class RentActivity : AppCompatActivity() {

    private lateinit var vm: RentViewModel
    private lateinit var binding: ActivityRentBinding

    lateinit var data: Equipment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this).get(RentViewModel::class.java)

        data = intent.getSerializableExtra("Data") as Equipment

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rent)
        binding.activity = this
        binding.lifecycleOwner = this

        vm.init(data, binding)

        binding.viewModel = vm

        Picasso.get().load(data.image).into(imageView_RentItem_RentActivity)

    }

    fun rentRequest(){
        val dialog = MyUtil.makeBaseDialog(this, "대여")

        dialog.textView_Cancel_AlertDialogLayout.setOnClickListener {
            dialog.dismiss()
        }

        dialog.textView_Accept_AlertDialogLayout.setOnClickListener {
            if(checkEditText()){
                vm.rentRequest(applicationContext, getEquipmentAllowDTO(), data.name)
                dialog.dismiss()
                finish()
            }
        }

        dialog.show()
    }

    private fun checkEditText(): Boolean {
        return if(vm.rentAmount == 0) {
            Toast.makeText(applicationContext, "대여 수량을 확인해 주세요", Toast.LENGTH_SHORT).show()
            false
        }else if(editText_RentReason_RentActivity.text.toString() == ""){
            Toast.makeText(applicationContext, "대여 사유를 입력해 주세요", Toast.LENGTH_SHORT).show()
            false
        }else{
            true
        }
    }

    private fun getEquipmentAllowDTO(): EquipmentAllowDTO {
        return EquipmentAllowDTO(vm.rentAmount, vm.rentReason)
    }

}