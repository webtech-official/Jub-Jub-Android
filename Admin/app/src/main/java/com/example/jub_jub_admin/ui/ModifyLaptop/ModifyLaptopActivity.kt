package com.example.jub_jub_admin.ui.ModifyLaptop

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.jub_jub_admin.R
import com.example.jub_jub_admin.data.remote.NetRetrofit
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus
import com.example.jub_jub_admin.entity.dataclass.body.LaptopSaveDTO
import com.example.jub_jub_admin.entity.dataclass.response.GETLaptopSpecResponse
import com.example.jub_jub_admin.entity.dataclass.response.MyResponse
import com.example.jub_jub_admin.entity.singleton.LaptopSpecManager
import com.example.jub_jub_admin.entity.singleton.TokenManager
import com.example.jub_jub_admin.ui.manageEq.ManageEquipmentViewModel
import kotlinx.android.synthetic.main.activity_modify_laptop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        button_Modify_ModifyLaptopActivity.setOnClickListener {
            addLaptop()
        }

//        getLaptopSpecArr()
        setSpinner(LaptopSpecManager.getSpecNameList())

    }

    private fun addLaptop() {

        val response = NetRetrofit.getServiceApi().addLaptop(TokenManager.token, getLaptopDTO())

        response.enqueue(object : Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.success){
                        Toast.makeText(applicationContext, "노트북 등록 성공", Toast.LENGTH_SHORT).show()
                        ManageEquipmentViewModel.update()
                        finish()

                    }else{
                        Toast.makeText(applicationContext, "실패했습니다. ${response.body()!!.msg}", Toast.LENGTH_SHORT).show()
                        Log.d("TestLog_ModifyLaptopActivity", "실패 ${response.body()!!.msg}")
                    }
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "서버 통신에 실패했습니다. ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("TestLog_ModifyLaptopActivity", "실패 ${t.message}")
            }

        })
    }

    private fun getLaptopDTO(): LaptopSaveDTO {
        return LaptopSaveDTO(
                editText_ClassNum_ModifyLaptopActivity.text.toString(),
                editText_LaptopSerialNum_ModifyLaptopActivity.text.toString(),
                spinner_LaptopSpec_ModifyLaptopActivity.selectedItemPosition+1,
                editText_StudentName_ModifyLaptopActivity.text.toString()
        )
    }

    private fun setSpinner(items: ArrayList<String>){
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spinner_LaptopSpec_ModifyLaptopActivity.adapter = myAdapter

        spinner_LaptopSpec_ModifyLaptopActivity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d("TestLog_ModifyLaptop", "$position 클릭")
                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {
                        Log.d("TestLog_ModifyLaptop", "클릭 1")
                    }
                    1 -> {
                        Log.d("TestLog_ModifyLaptop", "클릭 2")
                    }
                    //...
                    else -> {
                        Log.d("TestLog_ModifyLaptop", "클릭 ?")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
        }
    }

        private fun getLaptopSpecArr(): ArrayList<String> {
        val arr = ArrayList<String>()
        val response = NetRetrofit.getServiceApi().getLaptopSpec(TokenManager.getToken())

        response.enqueue(object : Callback<GETLaptopSpecResponse> {
            override fun onResponse(call: Call<GETLaptopSpecResponse>, response: Response<GETLaptopSpecResponse>) {
                if (response.isSuccessful) {
                    if (response.body()?.success == true) {
                        for (i in 0 until response.body()!!.list.size) {
                            // 이거 specName 생기면 수정할 예정
                            arr.add("${response.body()!!.list[i].specIdx}기")
                        }
                        Log.d("TestLog_ModifyLaptop", "추가 완료")
                    } else {
                        Toast.makeText(applicationContext, response.body()?.msg, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "실패했습니다. ${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GETLaptopSpecResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "서버통신에 실패했습니다. ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
        return arr
    }

    private fun modifyMode(data: LaptopStatus) {

        button_Modify_ModifyLaptopActivity.text = "수정"
        textView_ModifyMode_ModifyLaptopActivity.text = "수정"

        editText_StudentName_ModifyLaptopActivity.setText(data.studentName)
        editText_ClassNum_ModifyLaptopActivity.setText(data.classNumber)
        editText_LaptopSerialNum_ModifyLaptopActivity.setText(data.laptopSerialNumber)

        //스피너 관련 초기화 기능 구현 필요

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