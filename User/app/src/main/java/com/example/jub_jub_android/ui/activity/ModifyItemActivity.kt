package com.example.jub_jub_android.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.jub_jub_android.R
import com.example.jub_jub_android.entity.dataclass.ItemStatus
import com.example.jub_jub_android.ui.util.MyUtil
import kotlinx.android.synthetic.main.activity_modify_item.*

class ModifyItemActivity : AppCompatActivity(){

    var isModify = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_item)

        if(intent.hasExtra("Data")){
            isModify = true
            modifyMode(intent.getSerializableExtra("Data") as ItemStatus)
        }
        init()
    }

    private fun init() {

        imageView_OpenGallery_ModifyItemActivity.setOnClickListener {
            openGallery()
        }
        imageView_BackArrow_ModifyItemActivity.setOnClickListener {
            finish()
        }

    }

    private fun modifyMode(data: ItemStatus){
        imageView_ItemImage_ModifyActivity.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
        editText_ItemName_ModifyItemActivity.setText(data.name)
        editText_ItemCount_ModifyItemActivity.setText(data.count.toString())
        editText_ItemCategory_ModifyItemActivity.setText(data.category)

        textView_ModifyMode_ModifyItemActivity.text = "수정"
        button_Modify_ModifyItemActivity.text = "수정"
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