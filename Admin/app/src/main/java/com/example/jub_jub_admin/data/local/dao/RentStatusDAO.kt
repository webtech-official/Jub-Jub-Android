package com.example.jub_jub_admin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jub_jub_admin.entity.dataclass.StudentRentStatus

@Dao
interface RentStatusDAO {

    @Query("SELECT * FROM rentStatus")
    fun getAll(): List<StudentRentStatus>

    @Query("SELECT * FROM rentStatus WHERE status LIKE :word ")
    fun search(word: String): List<StudentRentStatus>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rentStatus: StudentRentStatus)

    @Query("DELETE from rentStatus")
    fun clear()


}