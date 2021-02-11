package com.example.jub_jub_user.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.jub_jub_user.R
import com.example.jub_jub_user.data.remote.NetRetrofit
import com.example.jub_jub_user.entity.dataclass.ItemStatus
import com.example.jub_jub_user.entity.dataclass.body.ModifyItem
import com.example.jub_jub_user.entity.dataclass.response.MyResponse
import com.example.jub_jub_user.entity.singleton.TokenManager
import com.example.jub_jub_user.ui.util.MyUtil
import kotlinx.android.synthetic.main.activity_modify_item.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyItemActivity : AppCompatActivity(){

    var isModify = false
    var isImageSelected = false
    lateinit var imageUri: Uri

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

        button_Modify_ModifyItemActivity.setOnClickListener {
            addData()
        }

    }

    private fun modifyMode(data: ItemStatus){
        imageView_ItemImage_ModifyActivity.setImageBitmap(MyUtil().convertBase64ToBitmap(data.image))
        editText_ItemName_ModifyItemActivity.setText(data.name)
        editText_ItemCount_ModifyItemActivity.setText(data.count.toString())
        editText_ItemCategory_ModifyItemActivity.setText(data.category)

        textView_ModifyMode_ModifyItemActivity.text = "수정"
        button_Modify_ModifyItemActivity.text = "수정"

        isImageSelected = true
    }

    private fun openGallery() {
        // 암시적 인텐트로 갤러리 열기
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 100)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 100){
            imageUri = data?.data!!

            //Uri 제대로 왔는지 테스트하는 코드
            imageView_ItemImage_ModifyActivity.setImageURI(imageUri)
            isImageSelected = true
            val image : Bitmap = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, imageUri)
        }
    }


    private fun addData() {
        if(checkEditText()){

            var file: String? = null

            var imageFile = MyUtil().uriToFile(imageUri, applicationContext)
            val filePath: MultipartBody.Part = MyUtil().createMultiPart(imageFile)

            val addItemData = ModifyItem(filePath, editText_ItemName_ModifyItemActivity.text.toString(), editText_ItemCategory_ModifyItemActivity.text.toString(), editText_ItemCount_ModifyItemActivity.text.toString().toInt())
            Log.d("TestLog", "${addItemData}")

            val response: Call<MyResponse> = NetRetrofit.getServiceApi().addItem(TokenManager.getToken(), filePath, editText_ItemName_ModifyItemActivity.text.toString(), editText_ItemCategory_ModifyItemActivity.text.toString(), editText_ItemCount_ModifyItemActivity.text.toString().toInt())

            response.enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    Log.d("TestLog", "message = ${response.message()} \n")
                    Log.d("TestLog", "success = ${response.body()?.success} \n")
                    Log.d("TestLog", "msg = ${response.body()?.msg} \n")
                    Log.d("TestLog", "code = ${response.body()?.code} \n")
                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    Log.d("TestLog", "Fail message = ${t.message} \n")

//                    Log.d("TestLog", "message = ${response.message()} \n")
//                    Log.d("TestLog", "success = ${response.body()?.success} \n")
//                    Log.d("TestLog", "msg = ${response.body()?.msg} \n")
//                    Log.d("TestLog", "code = ${response.body()?.code} \n")
                }

            })
        }
    }

    private fun checkEditText(): Boolean {
        return if(editText_ItemName_ModifyItemActivity.text.toString() == "" || editText_ItemCount_ModifyItemActivity.text.toString() == ""
                || editText_ItemCategory_ModifyItemActivity.text.toString() == "") {
            Toast.makeText(applicationContext, "빈칸을 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
            false
        }
        else if(!isImageSelected){
            Toast.makeText(applicationContext, "기자재 사진을 등록해주세요!", Toast.LENGTH_SHORT).show()
            false
        }
        else{
            Log.d("TestLog", "checkEditText 통과")
            true
        }
    }


}