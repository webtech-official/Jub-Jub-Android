package com.example.jub_jub_admin.data.local.dao

import androidx.room.*
import com.example.jub_jub_admin.entity.dataclass.ItemStatus

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