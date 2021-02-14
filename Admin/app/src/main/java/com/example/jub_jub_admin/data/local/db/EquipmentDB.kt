package com.example.jub_jub_admin.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_admin.data.local.dao.EquipmentDAO
import com.example.jub_jub_admin.entity.dataclass.Equipment


@Database(entities = [Equipment::class], version = 3)
abstract class EquipmentDB: RoomDatabase() {

    abstract fun equipmentDAO(): EquipmentDAO

    companion object {
        private var INSTANCE: EquipmentDB? = null

        fun getInstance(context: Context): EquipmentDB? {
            if (INSTANCE == null) {
                synchronized(EquipmentDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            EquipmentDB::class.java, "equipment.db")
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