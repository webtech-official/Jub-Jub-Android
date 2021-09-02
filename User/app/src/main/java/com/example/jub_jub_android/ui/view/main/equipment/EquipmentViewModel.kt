package com.example.jub_jub_android.ui.view.main.equipment

import androidx.lifecycle.MutableLiveData
import com.example.jub_jub_android.base.BaseViewModel
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.model.repository.EquipmentRepository
import com.example.jub_jub_android.ui.adapter.recyclerview.EquipmentRecyclerViewAdapter

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