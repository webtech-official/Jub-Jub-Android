package com.example.jub_jub_android.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jub_jub_android.entity.dataclass.LaptopStatus


@Dao
interface LaptopStatusDAO {

        @Query("SELECT * FROM laptopStatus")
        fun getAll(): List<LaptopStatus>

        @Query("SELECT * FROM laptopStatus WHERE name LIKE :word ")
        fun search(word: String): List<LaptopStatus>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(laptopStatus: LaptopStatus)

        @Query("DELETE from laptopStatus")
        fun clear()

}