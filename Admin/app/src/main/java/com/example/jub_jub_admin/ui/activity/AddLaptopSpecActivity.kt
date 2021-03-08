package com.example.jub_jub_admin.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.entity.dataclass.body.AddLaptopSpec
import com.example.jub_jub_admin.entity.dataclass.response.MyResponse
import com.example.jub_jub_admin.entity.singleton.LaptopSpecManager
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.util.MyUtil
import kotlinx.android.synthetic.main.activity_add_laptop_spec.*
import retrofit2.Call
import retrofit2.Response

class AddLaptopSpecActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_laptop_spec)

        init()
    }

    private fun init() {
        imageView_OpenGallery_AddLaptopSpecActivity.setOnClickListener {
            openGallery()
        }
        imageView_BackArrow_AddLaptopSpecActivity.setOnClickListener {
            finish()
        }
        button_Add_AddLaptopSpecActivity.setOnClickListener {
            addLaptopSpec()
        }
    }

    private fun addLaptopSpec() {

        val response = NetRetrofit.getServiceApi().addLaptopSpec(TokenManager.getToken(), getLaptopSpec())

        response.enqueue(object: retrofit2.Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if(response.isSuccessful){
                    if(response.body()?.success == true){
                        Toast.makeText(applicationContext, "노트북 사양 등록 성공!", Toast.LENGTH_SHORT).show()
                        LaptopSpecManager.getLaptopSpec()
                        finish()
                    }else{
                        Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_SHORT).show()
                    }
                    Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getLaptopSpec(): AddLaptopSpec {
        return AddLaptopSpec(
            editText_CPU_AddLaptopSpecActivity.text.toString(),
            editText_GPU_AddLaptopSpecActivity.text.toString(),
            editText_HDD_AddLaptopSpecActivity.text.toString(),
            editText_LaptopBrand_AddLaptopSpecActivity.text.toString(),
            editText_LaptopName_AddLaptopSpecActivity.text.toString(),
            editText_RAM_AddLaptopSpecActivity.text.toString(),
            editText_SSD_AddLaptopSpecActivity.text.toString()
        )
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

    private fun modifyMode(data: LaptopStatus) {
        editText_LaptopName_AddLaptopSpecActivity.setText(data.laptopSpec.laptopName)
        //imageView_ItemImage_AddLaptopSpecActivity.setImageBitmap(MyUtil.convertBase64ToBitmap(data.image))
        editText_CPU_AddLaptopSpecActivity.setText(data.laptopSpec.cpu)
        editText_GPU_AddLaptopSpecActivity.setText(data.laptopSpec.gpu)
        editText_RAM_AddLaptopSpecActivity.setText(data.laptopSpec.ram)
        editText_SSD_AddLaptopSpecActivity.setText(data.laptopSpec.ssd)
        editText_HDD_AddLaptopSpecActivity.setText(data.laptopSpec.hdd)

        //button_Modify_AddLaptopSpecActivity.text = "수정"
        //textView_ModifyMode_AddLaptopSpecActivity.text = "수정"
    }

}