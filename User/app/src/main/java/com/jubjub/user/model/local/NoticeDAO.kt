package com.jubjub.user.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jubjub.user.entity.dataclass.response.Notice

@Dao
interface NoticeDAO {

    @Query("SELECT * FROM notice")
    fun getAll(): List<Notice>

    @Query("SELECT * FROM notice WHERE notice_Idx LIKE :idx")
    fun getNoticeAt(idx: Int): Notice

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notice: Notice)

    @Query("DELETE from notice")
    fun clear()

}