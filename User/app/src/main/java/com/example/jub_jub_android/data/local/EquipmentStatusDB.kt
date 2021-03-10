package com.example.jub_jub_android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_android.entity.dataclass.Equipment


@Database(entities = [Equipment::class], version = 2, exportSchema = false)
abstract class EquipmentStatusDB: RoomDatabase() {

    abstract fun equipmentStatusDAO(): EquipmentStatusDAO

    companion object {
        private var INSTANCE: EquipmentStatusDB? = null

        fun getInstance(context: Context): EquipmentStatusDB? {
            if (INSTANCE == null) {
                synchronized(EquipmentStatusDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            EquipmentStatusDB::class.java, "equipmentStatus.db")
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