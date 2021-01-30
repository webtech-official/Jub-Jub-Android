package com.example.jub_jub_android.data.local.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_android.data.local.DAO.RentRequestDAO
import com.example.jub_jub_android.entity.dataclass.RentRequest


@Database(entities = [RentRequest::class], version = 1)

abstract class RentRequestDB: RoomDatabase() {

    abstract fun rentRequestDAO(): RentRequestDAO

    companion object {
        private var INSTANCE: RentRequestDB? = null

        fun getInstance(context: Context): RentRequestDB? {
            if (INSTANCE == null) {
                synchronized(ItemStatusDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RentRequestDB::class.java, "rentRequest.db")
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