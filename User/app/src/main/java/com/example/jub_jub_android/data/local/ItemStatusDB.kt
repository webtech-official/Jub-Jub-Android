package com.example.jub_jub_android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_android.entity.dataclass.Equipment


@Database(entities = [Equipment::class], version = 2, exportSchema = false)
abstract class ItemStatusDB: RoomDatabase() {

    abstract fun itemStatusDAO(): ItemStatusDAO

    companion object {
        private var INSTANCE: ItemStatusDB? = null

        fun getInstance(context: Context): ItemStatusDB? {
            if (INSTANCE == null) {
                synchronized(ItemStatusDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ItemStatusDB::class.java, "itemStatus.db")
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