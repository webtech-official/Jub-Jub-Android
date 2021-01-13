package com.example.jup_jup_android.ui.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.jup_jup_android.R
import com.example.jup_jup_android.entity.dataclass.ItemStatus
import com.example.jup_jup_android.entity.singleton.ItemStatusListManager
import kotlinx.android.synthetic.main.activity_rent.*
import kotlinx.android.synthetic.main.layout_alertdialog.*

class RentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)

        val pageIndex = intent.getIntExtra("PageIndex", 0)
        val itemIndex = intent.getIntExtra("ItemIndex", 0)

        val data: ItemStatus = ItemStatusListManager.getDevidedItemStatusList()[pageIndex][itemIndex]

        setTextViewsText(data)


        Log.d("TestLog", "Index = $pageIndex, $itemIndex")

        textView_AddMyRentItemAmount_RentActivity.setOnClickListener {
            if(textView_MyRentItemAmount_RentActivity.text.toString().toInt() < data.itemCount){
                textView_MyRentItemAmount_RentActivity.text = "${textView_MyRentItemAmount_RentActivity.text.toString().toInt() + 1}"
            }

        }

        textView_MinusMyRentItemAmoun_RentActivityt.setOnClickListener {
            if(textView_MyRentItemAmount_RentActivity.text.toString().toInt() != 1) {
                textView_MyRentItemAmount_RentActivity.text = "${textView_MyRentItemAmount_RentActivity.text.toString().toInt() - 1}"
            }
        }

        textView_Rent_RentActivity.setOnClickListener {
            var dialog = Dialog(this)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.layout_alertdialog)

            dialog.textView_AlertName_AlertDialogLayout.text = data.itemName
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
        textView_rentItemName_RentActivity.text = data.itemName
        textView_rentItemCategory_RentActivity.text = data.itemCategory
        textView_RentItemCount_RentActivity.text = "수량: ${data.itemCount}개"
        imageView_RentItem_RentActivity.setImageBitmap(data.itemImage)
    }
}