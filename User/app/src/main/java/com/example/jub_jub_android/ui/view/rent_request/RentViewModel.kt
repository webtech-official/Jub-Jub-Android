package com.example.jub_jub_android.ui.view.rent_request

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.jub_jub_android.base.BaseViewModel
import com.example.jub_jub_android.model.network.NetRetrofit
import com.example.jub_jub_android.databinding.ActivityRentBinding
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.entity.dataclass.response.EquipmentAllowDTO
import com.example.jub_jub_android.entity.dataclass.response.MyResponse
import com.example.jub_jub_android.entity.singleton.TokenManager
import com.example.jub_jub_android.ui.view.equipment_status.EquipmentStatus_ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentViewModel: BaseViewModel() {

    var itemAmount = 0
    var rentAmount = 0
    var rentReason = ""
    lateinit var binding : ActivityRentBinding

    fun init(data: Equipment, binding: ActivityRentBinding){
        itemAmount = data.count
        this.binding = binding
    }

    fun rentRequest(context: Context, equipmentAllowDTO: EquipmentAllowDTO, eqName: String) {
        val response: Call<MyResponse> = NetRetrofit.getEquipmentApi().rentEquipment(TokenManager.getToken(), equipmentAllowDTO, eqName)

        response.enqueue(object : Callback<MyResponse> {
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {

                if(response.isSuccessful && response.body()!!.success){
                    Toast.makeText(context, "대여 신청 완료!", Toast.LENGTH_SHORT).show()
                    EquipmentStatus_ViewModel.update()
                } else{
                    Toast.makeText(context, "${response.body()?.msg}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun plusItem(){
        Log.d("TestLog_RVM", "Plus 클릭, rA = $rentAmount, iA = $itemAmount")
        if(rentAmount < itemAmount){
            rentAmount++
            binding.invalidateAll()
        }
    }

    fun minusItem(){
        Log.d("TestLog_RVM", "Minus 클릭, rA = $rentAmount, iA = $itemAmount")
        if(rentAmount > 1) {
            rentAmount--
            binding.invalidateAll()
        }
    }

}