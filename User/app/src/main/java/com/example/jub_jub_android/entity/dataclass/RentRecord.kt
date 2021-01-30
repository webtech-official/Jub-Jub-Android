package com.example.jub_jub_android.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Date

@Entity(tableName = "rentRecord")
data class RentRecord(
    @PrimaryKey(autoGenerate = true) var rentID: Int,
    val name:String,
    val image: String,
    val category: String,
    val studentNumber: Int,
    val count: String,
    val date: String
): Serializable{
    constructor(name: String, image: String, category: String, studentNumber: Int, count: String, date: String)
    : this(0, name, image, category, studentNumber, count, date)
}
