package com.example.jub_jub_android.ui.view.main.myrent

import androidx.lifecycle.MutableLiveData
import com.example.jub_jub_android.base.BaseViewModel
import com.example.jub_jub_android.entity.dataclass.MyEquipment
import com.example.jub_jub_android.entity.dataclass.response.MyEquipmentDetailInfo
import com.example.jub_jub_android.model.repository.MyRentRepository
import com.example.jub_jub_android.ui.adapter.recyclerview.RentListRecyclerViewAdapter

class MyRentViewModel(private val myRent: MyRentRepository): BaseViewModel() {

    var showStatus = MutableLiveData<String>("전체")

    var myRentArrayList = MutableLiveData<ArrayList<MyEquipment>>(myRent.createDummyDataArrayList())

    var adapter = RentListRecyclerViewAdapter(myRentArrayList.value!!)



//    fun getMyEquipmentData(context: Context) {
//        val response: Call<MyEquipmentResponse> = NetRetrofit.getEquipmentApi().getMyEquipmentData(TokenManager.getToken())
//
//        response.enqueue(object: Callback<MyEquipmentResponse>{
//
//            override fun onResponse(call: Call<MyEquipmentResponse>, response: Response<MyEquipmentResponse>) {
//                if(response.isSuccessful && response.body()!!.success){
//                    setDataList(response.body()?.list!!)
//                }else{
//                    Toast.makeText(context, response.body()?.msg, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<MyEquipmentResponse>, t: Throwable) {
//                Toast.makeText(context, t.message!!, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }


    private fun getStatus(data: MyEquipmentDetailInfo): String {
        return when(data.equipmentEnum){
            "ROLE_Waiting" -> "대기"
            "ROLE_Accept" -> "승인"
            "ROLE_Reject" -> "거절"
            "ROLE_Rental" -> "대여"
            "ROLE_Return" -> "반납"
            //대여, 반납, 연체 추가
            else -> "?"
        }
    }

    //region change Rent Status View Mode
    fun changeRSVM(){
        showStatus.value =
            when(showStatus.value){
                "전체" -> "대기"
                "대기" -> "대여"
                "대여" -> "승인"
                "승인" -> "반납"
                "반납" -> "거절"
                "거절" -> "전체"
                else -> "?"
            }
    }
    //endregion

}