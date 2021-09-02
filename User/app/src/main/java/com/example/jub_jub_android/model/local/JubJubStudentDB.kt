package com.example.jub_jub_android.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_android.entity.dataclass.Equipment
import com.example.jub_jub_android.entity.dataclass.MyEquipment

@Database(entities = [MyEquipment::class, Equipment::class],  version = 3, exportSchema = false)
abstract class JubJubStudentDB: RoomDatabase() {

    abstract fun equipmentStatusDAO(): EquipmentStatusDAO
    abstract fun myEquipmentDAO(): MyEquipmentDAO

    companion object {
        private var INSTANCE: JubJubStudentDB? = null

        fun getInstance(context: Context): JubJubStudentDB? {
            if (INSTANCE == null) {
                synchronized(JubJubStudentDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        JubJubStudentDB::class.java, "jubjubStudent.db")
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