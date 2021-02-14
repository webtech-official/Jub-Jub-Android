package com.example.jub_jub_admin.data.local.dao

import androidx.room.*
import com.example.jub_jub_admin.entity.dataclass.Equipment

@Dao
interface EquipmentDAO {

    @Query("SELECT * FROM equipment")
    fun getAll(): List<Equipment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(equipment: Equipment)


    @Query("SELECT * FROM equipment WHERE name || category  LIKE :word ")
    fun search(word: String): List<Equipment>

    @Query("DELETE from equipment")
    fun clear()

}