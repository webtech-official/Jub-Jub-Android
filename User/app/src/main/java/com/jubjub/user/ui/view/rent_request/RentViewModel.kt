package com.jubjub.user.ui.view.rent_request

import androidx.lifecycle.MutableLiveData
import com.jubjub.user.base.BaseViewModel
import com.jubjub.user.entity.dataclass.Equipment
import com.jubjub.user.entity.dataclass.response.EquipmentRentRequestDTO
import com.jubjub.user.entity.dataclass.result.ServerResult
import com.jubjub.user.model.repository.EquipmentRepository

class RentViewModel(private val equipment: EquipmentRepository): BaseViewModel() {

    var rentAmount = MutableLiveData(1)
    var rentReason = ""

    lateinit var equipmentData : Equipment

    var rentRequestResult = MutableLiveData<ServerResult>()

    fun rentRequest() {
        addDisposable(
            equipment.rentRequest(createEquipmentAllowDTO(), equipmentData.name)
                .subscribe({
                    rentRequestResult.postValue(ServerResult(it.success, it.msg))
                },{
                    rentRequestResult.postValue(ServerResult(false, "${it.message}"))
                })
        )
    }

    private fun createEquipmentAllowDTO(): EquipmentRentRequestDTO {
        return EquipmentRentRequestDTO(rentAmount.value!!, rentReason)
    }


    fun clickPlusButton(){
        showLog("RentViewModel_Plus 클릭 rA = $rentAmount, iA = ${equipmentData.count}")
        if(rentAmount.value!! < equipmentData.count){
            rentAmount.postValue(rentAmount.value?.plus(1))
        }
    }

    fun clickMinusButton(){
        showLog("RentViewModel_Minus 클릭 rA = $rentAmount, iA = ${equipmentData.count}")
        if(rentAmount.value!! > 1) {
            rentAmount.postValue(rentAmount.value?.minus(1))
        }
    }



}