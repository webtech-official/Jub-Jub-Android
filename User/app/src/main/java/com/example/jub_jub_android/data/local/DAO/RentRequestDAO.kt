package com.example.jub_jub_android.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jub_jub_android.entity.dataclass.RentRequest

@Dao
interface RentRequestDAO {

    @Query("SELECT * FROM RentRequest")
    fun getAll(): List<RentRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rentRequest: RentRequest)


    @Query("SELECT * FROM RentRequest WHERE itemName || category || studentNumber  LIKE :word ")
    fun search(word: String): List<RentRequest>

    @Query("DELETE from RentRequest")
    fun clear()

}