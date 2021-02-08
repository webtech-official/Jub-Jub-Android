package com.example.jub_jub_android.entity.dataclass.response

import com.example.jub_jub_android.entity.dataclass.Equipment

data class MyEquipmentResponse (
        var code: Int,
        var list: ArrayList<DetailInfoOfMyequipment>,
        var msg: String,
        var success: Boolean
)

data class DetailInfoOfMyequipment(
        var admin: ArrayList<IDontKnowWhatisit>
)

class IDontKnowWhatisit {

}
