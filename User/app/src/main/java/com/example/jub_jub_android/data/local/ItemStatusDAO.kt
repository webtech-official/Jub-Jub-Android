package com.example.jub_jub_android.data.local

import androidx.room.*
import com.example.jup_jup_android.entity.dataclass.ItemStatus

@Dao
interface ItemStatusDAO {

    @Query("SELECT * FROM itemStatus")
    fun getAll(): List<ItemStatus>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemStatus: ItemStatus)


    @Query("SELECT * FROM itemStatus WHERE name || category  LIKE :word ")
    fun search(word: String): List<ItemStatus>

    @Query("DELETE from itemStatus")
    fun clear()

}