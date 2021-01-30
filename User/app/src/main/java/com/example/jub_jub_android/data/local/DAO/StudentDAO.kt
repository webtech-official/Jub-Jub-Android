package com.example.jub_jub_android.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jub_jub_android.entity.dataclass.Student

@Dao
interface StudentDAO {

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student)


    @Query("SELECT * FROM student WHERE name || number  LIKE :word ")
    fun search(word: String): List<Student>

    @Query("DELETE from student")
    fun clear()

}