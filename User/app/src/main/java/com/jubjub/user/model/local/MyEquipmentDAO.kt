package com.jubjub.user.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jubjub.user.entity.dataclass.MyEquipment

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