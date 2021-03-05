package com.example.jub_jub_admin.data.local.db

import android.content.Context
import androidx.room.*
import com.example.jub_jub_admin.data.local.dao.LaptopStatusDAO
import com.example.jub_jub_admin.entity.dataclass.Converters
import com.example.jub_jub_admin.entity.dataclass.LaptopStatus


@Database(entities = [LaptopStatus::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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