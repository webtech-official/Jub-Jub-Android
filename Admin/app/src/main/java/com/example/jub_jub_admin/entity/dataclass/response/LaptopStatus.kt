package com.example.jub_jub_admin.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.jub_jub_admin.entity.dataclass.response.LaptopSpec
import com.google.gson.Gson
import java.io.Serializable

data class GetLaptopResponse(
        val success: Boolean,
        val code: Int,
        val msg: String,
        val list: ArrayList<LaptopStatus>
)

@Entity(tableName = "laptopStatus")
data class LaptopStatus(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val createdDate : String,
    val modifiedDate: String,
    val laptopIdx: Int,
    val laptopSerialNumber: String,
    val studentName: String,
    val classNumber: String,
    val laptopSpec: LaptopSpec
): Serializable{
    constructor(createdDate: String, modifiedDate: String, laptopIdx: Int, laptopSerialNumber: String, studentName: String, classNumber: String, laptopSpec: LaptopSpec)
            : this(0, createdDate, modifiedDate, laptopIdx, laptopSerialNumber, studentName, classNumber, laptopSpec)
}

class Converters {

    @TypeConverter
    fun objectToJson(value: LaptopSpec): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToObject(value: String): LaptopSpec = Gson().fromJson(value, LaptopSpec::class.java)
}




