package com.example.jub_jub_admin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jub_jub_admin.entity.dataclass.RentRecord

@Dao
interface RentRecordDAO {

    @Query("SELECT * FROM rentRecord")
    fun getAll(): List<RentRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rentRecord: RentRecord)

    @Query("SELECT * FROM rentRecord WHERE name || category || studentNumber || date LIKE :word ")
    fun search(word: String): List<RentRecord>

    @Query("DELETE from rentRecord")
    fun clear()

}