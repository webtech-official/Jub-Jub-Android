package com.example.jub_jub_android.ui.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jub_jub_android.R
import com.example.jub_jub_android.data.remote.NetRetrofit
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.entity.dataclass.response.EquipmentAllowDTO
import com.example.jub_jub_android.entity.dataclass.response.MyResponse
import com.example.jub_jub_android.entity.singleton.TokenManager
import com.example.jub_jub_android.ui.util.MyUtil
import kotlinx.android.synthetic.main.activity_rent.*
import kotlinx.android.synthetic.main.layout_alertdialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)

        val data: Equipment = intent.getSerializableExtra("Data") as Equipment

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


            val dialog = MyUtil.makeBaseDialog(this, "대여")

            dialog.textView_Cancel_AlertDialogLayout.setOnClickListener {
                dialog.dismiss()
            }
            dialog.textView_Accept_AlertDialogLayout.setOnClickListener {
                rentRequest(data)
                dialog.dismiss()
                finish()
            }

            dialog.show()

        }
    }

    private fun rentRequest(data: Equipment) {
        val response: Call<MyResponse> = NetRetrofit.getServiceApi().rentEquipment(TokenManager.getToken(), EquipmentAllowDTO(textView_MyRentItemAmount_RentActivity.text.toString().toInt(), ""), data.name)


        response.enqueue(object : Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {

                if(response.body()?.success == true)
                    Toast.makeText(applicationContext, "대여 신청 완료!", Toast.LENGTH_SHORT).show()
                else{
                    Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun setTextViewsText(data: Equipment) {

        textView_rentItemName_RentActivity.text = data.name
        textView_rentItemCategory_RentActivity.text = data.category
        textView_RentItemCount_RentActivity.text = "수량: ${data.count}개"

        val decodedString: ByteArray = Base64.decode(data.image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        imageView_RentItem_RentActivity.setImageBitmap(decodedByte)
    }
}