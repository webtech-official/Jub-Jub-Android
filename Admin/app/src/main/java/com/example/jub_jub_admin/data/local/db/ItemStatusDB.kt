package com.example.jub_jub_admin.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_admin.data.local.dao.ItemStatusDAO
import com.example.jub_jub_admin.entity.dataclass.ItemStatus


@Database(entities = [ItemStatus::class], version = 1)
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