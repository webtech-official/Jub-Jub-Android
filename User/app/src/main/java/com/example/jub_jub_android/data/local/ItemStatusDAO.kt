package com.example.jub_jub_android.data.local

import androidx.room.*
import com.example.jub_jub_android.entity.dataclass.Equipment

@Dao
interface ItemStatusDAO {

    @Query("SELECT * FROM itemStatus")
    fun getAll(): List<Equipment>

    @Query("SELECT * FROM itemStatus WHERE name || category  LIKE :word ")
    fun search(word: String): List<Equipment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(equipment: Equipment)

    @Query("DELETE from itemStatus")
    fun clear()

}