package com.example.jub_jub_user.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_user.data.local.dao.StudentDAO
import com.example.jub_jub_user.entity.dataclass.Student


@Database(entities = [Student::class], version = 1)
abstract class StudentDB: RoomDatabase() {
    abstract fun studentDAO(): StudentDAO

    companion object {
        private var INSTANCE: StudentDB? = null

        fun getInstance(context: Context): StudentDB? {
            if (INSTANCE == null) {
                synchronized(StudentDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        StudentDB::class.java, "student.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }

    }
}