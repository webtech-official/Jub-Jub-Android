package com.jubjub.user.ui.view.rent_request

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.jubjub.user.R
import com.jubjub.user.base.BaseActivity
import com.jubjub.user.databinding.ActivityRentBinding
import com.jubjub.user.databinding.DialogRentRequestCompleteBinding
import com.jubjub.user.entity.dataclass.Equipment
import com.jubjub.user.entity.dataclass.result.RentRequestDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentActivity: BaseActivity<ActivityRentBinding, RentViewModel>(R.layout.activity_rent) {

    override val viewModel: RentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.equipmentData = intent.getSerializableExtra("EquipmentData") as Equipment

        binding.activity = this

        viewModel.rentRequestResult.observe(this, {
            showToast(it.msg)
            if(it.success){
                bindDialog("대여신청이 완료되었습니다!", "목록으로", true)
            }else{
                bindDialog("수량 부족으로 인해 대여 불가능합니다 !", "확인", false)
            }
        })

    }

    fun clickRentRequestButton(){
        if(checkEditText()){
            viewModel.rentRequest()
        }
    }

    private fun checkEditText(): Boolean {
        return if(viewModel.rentAmount.value == 0) {
            bindDialog("대여 수량을 확인해주세요.", "확인", false)
            false
        }else if(viewModel.rentReason == ""){
            bindDialog("대여 사유을 입력해주세요.", "확인", false)
            false
        }else{
            true
        }
    }

    private fun bindDialog(result: String, button_Text: String, success: Boolean){

        var bindingDialog = DialogRentRequestCompleteBinding.inflate(LayoutInflater.from(this))

        var dialog = AlertDialog.Builder(this)
            .setView(bindingDialog.root)
            .create()

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        //dialog.window?.attributes?.height = ViewGroup.LayoutParams.MATCH_PARENT

        bindingDialog.dialog = RentRequestDialog(result, button_Text)
        //dialog.setContentView(bindingDialog.root)

        dialog.show()
    }

    private fun showDialog(result: String, button_Text: String){
        var dialog = Dialog(this)

        //val dialogBinding = DataBindingUtil.inflate<DialogRentRequestCompleteBinding>(LayoutInflater.from(this), R.layout.dialog_rent_request_complete, null, false);
        //val dialogBinding = DialogRentRequestCompleteBinding.inflate(LayoutInflater.from(this))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_rent_request_complete)

        dialog.findViewById<TextView>(R.id.textView_Dialog_Title).text = result


        dialog.findViewById<Button>(R.id.button_Dialog).also {
            it.text = button_Text

            if(button_Text == "목록으로") {
                it.setOnClickListener {
                    dialog.dismiss()
                    finish()
                }
            }

            if(button_Text == "확인") {
                it.setOnClickListener {
                    dialog.dismiss()
                }
            }

        }

            //dialog.textView_AlertName_AlertDialogLayout.text = dialogName
            //dialog.textView_AlertContent_AlertDialogLayout.text = "정말 $dialogName 하시겠습니까?"

            dialog.show()
        }

    }