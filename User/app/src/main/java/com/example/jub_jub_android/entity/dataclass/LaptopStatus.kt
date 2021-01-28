package com.example.jub_jub_android.entity.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "laptopStatus")
data class LaptopStatus(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var category: String,
    var count: Int,
    var image: String,
    var cpu: String,
    var gpu: String,
    var ram: String,
    var ssd: String,
    var hdd: String,
): Serializable{
    constructor(name: String, category: String, count: Int, image: String, cpu: String, gpu: String, ram: String, ssd: String, hdd: String)
            : this(0, name, category, count, image, cpu, gpu, ram, ssd, hdd)
}
