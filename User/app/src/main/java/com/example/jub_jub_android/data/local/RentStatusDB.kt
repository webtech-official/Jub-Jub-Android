package com.example.jub_jub_android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_android.entity.dataclass.RentStatus


@Database(entities = [RentStatus::class], version = 1)
abstract class RentStatusDB: RoomDatabase() {

    abstract fun rentStatusDAO() : RentStatusDAO

    companion object {
        private var INSTANCE: RentStatusDB? = null

        fun getInstance(context: Context): RentStatusDB? {
            if (INSTANCE == null) {
                synchronized(RentStatusDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RentStatusDB::class.java, "rentStatus.db")
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