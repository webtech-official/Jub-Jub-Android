package com.example.jub_jub_user.ui.activity

import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jup_jup_android.R
import com.example.jub_jub_user.entity.dataclass.ItemStatus
import kotlinx.android.synthetic.main.activity_rent.*
import kotlinx.android.synthetic.main.layout_alertdialog.*


class RentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)

        val data: ItemStatus = intent.getSerializableExtra("Data") as ItemStatus

        setTextViewsText(data)

        imageView_BackArrow_RentActivity.setOnClickListener {
            finish()
        }

        textView_AddMyRentItemAmount_RentActivity.setOnClickListener {
            if(textView_MyRentItemAmount_RentActivity.text.toString().toInt() < data.count){
                textView_MyRentItemAmount_RentActivity.text = "${textView_MyRentItemAmount_RentActivity.text.toString().toInt() + 1}"
            }

        }

        textView_MinusMyRentItemAmoun_RentActivityt.setOnClickListener {
            if(textView_MyRentItemAmount_RentActivity.text.toString().toInt() != 1) {
                textView_MyRentItemAmount_RentActivity.text = "${textView_MyRentItemAmount_RentActivity.text.toString().toInt() - 1}"
            }
        }

        button_Rent_RentActivity.setOnClickListener {
            var dialog = Dialog(this)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.layout_alertdialog)

            dialog.textView_AlertName_AlertDialogLayout.text = data.name
            dialog.textView_AlertContent_AlertDialogLayout.text = "정말 대여 하시겠습니까?"
            dialog.show()

            dialog.textView_Cancel_AlertDialogLayout.setOnClickListener {
                dialog.dismiss()
            }
            dialog.textView_Accept_AlertDialogLayout.setOnClickListener {
                Toast.makeText(applicationContext, "대여 신청 완료!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                finish()
            }


        }

    }

    private fun setTextViewsText(data: ItemStatus) {
        textView_rentItemName_RentActivity.text = data.name
        textView_rentItemCategory_RentActivity.text = data.category
        textView_RentItemCount_RentActivity.text = "수량: ${data.count}개"


        val decodedString: ByteArray = Base64.decode(data.image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        imageView_RentItem_RentActivity.setImageBitmap(decodedByte)
    }
}