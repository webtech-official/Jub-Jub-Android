package com.jubjub.user.ui.view.main.equipment

import androidx.lifecycle.MutableLiveData
import com.jubjub.user.base.BaseViewModel
import com.jubjub.user.entity.dataclass.Equipment
import com.jubjub.user.model.repository.EquipmentRepository
import com.jubjub.user.ui.adapter.recyclerview.EquipmentRecyclerViewAdapter

class EquipmentViewModel(private val equipment: EquipmentRepository): BaseViewModel() {

    var searchText = MutableLiveData("")

    var equipmentArrayList = MutableLiveData<ArrayList<Equipment>>(equipment.createDummyDataArrayList())

    var adapter = equipmentArrayList.value?.let { EquipmentRecyclerViewAdapter(it) }

    fun updateEquipmentData(){
    addDisposable(
        equipment.getEquipmentData()
            .subscribe({
                if(it.success){
                    equipmentArrayList.postValue(it.list)
                }
            },{
                showLog(it.message.toString())
            })
    )  }

}