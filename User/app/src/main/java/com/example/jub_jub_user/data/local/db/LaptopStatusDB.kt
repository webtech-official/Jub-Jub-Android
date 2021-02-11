package com.example.jub_jub_user.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_user.data.local.dao.LaptopStatusDAO
import com.example.jub_jub_user.entity.dataclass.LaptopStatus


@Database(entities = [LaptopStatus::class], version = 1)
abstract class LaptopStatusDB: RoomDatabase() {

    abstract fun laptopStatusDAO(): LaptopStatusDAO

    companion object {
        private var INSTANCE: LaptopStatusDB? = null

        fun getInstance(context: Context): LaptopStatusDB? {
            if (INSTANCE == null) {
                synchronized(LaptopStatusDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        LaptopStatusDB::class.java, "laptopStatus.db")
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