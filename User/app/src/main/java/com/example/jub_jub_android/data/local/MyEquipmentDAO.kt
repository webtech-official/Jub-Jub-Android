package com.example.jub_jub_android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jub_jub_android.entity.dataclass.MyEquipment

@Dao
interface MyEquipmentDAO {

    @Query("SELECT * FROM myEquipment")
    fun getAll(): List<MyEquipment>

    @Query("SELECT * FROM myEquipment WHERE status LIKE :word ")
    fun search(word: String): List<MyEquipment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(myEquipment: MyEquipment)

    @Query("DELETE from myEquipment")
    fun clear()


}