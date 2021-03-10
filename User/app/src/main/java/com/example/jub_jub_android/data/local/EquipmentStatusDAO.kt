package com.example.jub_jub_android.data.local

import androidx.room.*
import com.example.jub_jub_android.entity.dataclass.Equipment

@Dao
interface EquipmentStatusDAO {

    @Query("SELECT * FROM equipmentStatus")
    fun getAll(): List<Equipment>

    @Query("SELECT * FROM equipmentStatus WHERE name || category  LIKE :word ")
    fun search(word: String): List<Equipment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(equipment: Equipment)

    @Query("DELETE from equipmentStatus")
    fun clear()

}