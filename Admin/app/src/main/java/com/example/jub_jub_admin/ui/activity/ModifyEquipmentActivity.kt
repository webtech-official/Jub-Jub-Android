package com.example.jub_jub_admin.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.Equipment
import com.example.jub_jub_admin.entity.dataclass.response.MyResponse
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.manageEq.ManageEquipmentViewModel
import com.example.jub_jub_admin.ui.util.MyUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_modify_item.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URI


class ModifyEquipmentActivity : AppCompatActivity(){

    var isModify = false
    var isImageSelected = false
    lateinit var imageUri: Uri
    lateinit var data: Equipment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_item)

        if(intent.hasExtra("Data")){
            isModify = true
            data = intent.getSerializableExtra("Data") as Equipment

            Log.d("TestLog_ModifyEquip", "data.image = ${data.image}")

            modifyMode(data)
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
            if(isModify){
                modifyData()
            } else{
                addData()
            }
        }

    }
    private fun addData() {
        if(checkEditText()){
            progress_bar.visibility = View.VISIBLE

            val imagePath = MyUtil.getPathFromUri(applicationContext, imageUri)
            val imageFile = File(imagePath)
            val imageRequestBody = imageFile.asRequestBody("image/png".toMediaTypeOrNull())
            val imageBody = MultipartBody.Part.createFormData("img_equipment", imageFile.name, imageRequestBody)

            val name = RequestBody.create("text/plain".toMediaTypeOrNull(), editText_ItemName_ModifyItemActivity.text.toString())
            val category = RequestBody.create("text/plain".toMediaTypeOrNull(), editText_ItemCategory_ModifyItemActivity.text.toString())
            val count = RequestBody.create("text/plain".toMediaTypeOrNull(), editText_ItemCount_ModifyItemActivity.text.toString())

            val response: Call<MyResponse> = NetRetrofit.getServiceApi().addItem(TokenManager.getToken(), imageBody, name, category, count)
            response.enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    progress_bar.visibility = View.GONE
                    imageFile.delete()
                    if (response.isSuccessful) {
                        if (response.body()?.success == true) {
                            Toast.makeText(applicationContext, "기자재 등록 완료", Toast.LENGTH_SHORT).show()
                            ManageEquipmentViewModel.update()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    progress_bar.visibility = View.GONE
                    imageFile.delete()
                    Log.d("TestLog_ModifyEQ", "Fail message = ${t.message} \n")
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

                }

            })
        }
    }

    private fun modifyData() {
        progress_bar.visibility = View.VISIBLE
        Log.d("TestLog_Modify", "Image = ${MyUtil.getUriFromBitmap(applicationContext, imageView_ItemImage_ModifyActivity.drawable.toBitmap()).toString()}")
        val imagePath = MyUtil.getPathFromUri(applicationContext, MyUtil.getUriFromBitmap(applicationContext, imageView_ItemImage_ModifyActivity.drawable.toBitmap()))
        val imageFile = File(imagePath)
        val imageRequestBody = imageFile.asRequestBody("image/png".toMediaTypeOrNull())
        Log.d("TestLog_Modify", "ImageName = ${imageFile.name}")
        val imageBody = MultipartBody.Part.createFormData("img_equipment", imageFile.name, imageRequestBody)

        val newName = RequestBody.create("text/plain".toMediaTypeOrNull(), editText_ItemName_ModifyItemActivity.text.toString())
        val category = RequestBody.create("text/plain".toMediaTypeOrNull(), editText_ItemCategory_ModifyItemActivity.text.toString())
        val count = editText_ItemCount_ModifyItemActivity.text.toString().toInt()
        Log.d("TestLog_Modify", data.name)

        val response :Call<MyResponse> = NetRetrofit.getServiceApi().modifyEquipment(TokenManager.getToken(), imageBody, data.name, category, count, newName)

        response.enqueue(object : Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {

                progress_bar.visibility = View.GONE
                Log.d("TestLog_ModifyAc", "Delete ImageFile")
                //imageFile.delete()
                //imageFile.absoluteFile.delete()

                if (response.isSuccessful) {
                    if (response.body()?.success == true) {
                        Toast.makeText(applicationContext, "기자재 수정 완료", Toast.LENGTH_SHORT).show()
                        ManageEquipmentViewModel.update()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "기자재 등록 실패! \n ${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                progress_bar.visibility = View.GONE
                imageFile.delete()
                Log.d("TestLog_ModifyEquip", "Fail!, ${t.message}")
            }

        })

    }

    private fun modifyMode(data: Equipment){
        Picasso.get().load(data.image).into(imageView_ItemImage_ModifyActivity)
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