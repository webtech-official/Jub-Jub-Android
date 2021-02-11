package com.example.jub_jub_user.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.jub_jub_user.R
import com.example.jub_jub_user.entity.dataclass.LaptopStatus
import com.example.jub_jub_user.ui.util.MyUtil
import kotlinx.android.synthetic.main.activity_modify_laptop.*

class ModifyLaptopActivity : AppCompatActivity() {
    var isModify = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_laptop)

        if(intent.hasExtra("Data")){
            isModify = true
            modifyMode(intent.getSerializableExtra("Data") as LaptopStatus)
        }

        init()
    }

    private fun init() {
        imageView_OpenGallery_ModifyLaptopActivity.setOnClickListener {
            openGallery()
        }
        imageView_BackArrow_ModifyLaptopActivity.setOnClickListener {
            finish()
        }
    }

    private fun modifyMode(data: LaptopStatus) {
        editText_LaptopName_ModifyLaptopActivity.setText(data.name)
        editText_LaptopCount_ModifyLaptopActivity.setText(data.count.toString())
        imageView_ItemImage_ModifyLaptopActivity.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
        editText_CPU_ModifyLaptopActivity.setText(data.cpu)
        editText_GPU_ModifyLaptopActivity.setText(data.gpu)
        editText_RAM_ModifyLaptopActivity.setText(data.ram)
        editText_SSD_ModifyLaptopActivity.setText(data.ssd)
        editText_HDD_ModifyLaptopActivity.setText(data.hdd)

        button_Modify_ModifyLaptopActivity.text = "수정"
        textView_ModifyMode_ModifyLaptopActivity.text = "수정"
    }


    private fun openGallery() {
        // 암시적 인텐트로 갤러리 열기
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 100)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 100){
            val imageUri : Uri? = data?.data

            //Uri 제대로 왔는지 테스트하는 코드
            //imageView.setImageURI(imageUri)

            val image : Bitmap = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, imageUri)

        }
    }
}