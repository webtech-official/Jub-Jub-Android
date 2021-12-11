package com.jubjub.user.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jubjub.user.entity.dataclass.Equipment
import com.jubjub.user.entity.dataclass.MyEquipment
import com.jubjub.user.entity.dataclass.response.Notice

@Database(entities = [MyEquipment::class, Equipment::class, Notice::class],  version = 3, exportSchema = false)
abstract class JubJubStudentDB: RoomDatabase() {

    abstract fun equipmentStatusDAO(): EquipmentStatusDAO
    abstract fun myEquipmentDAO(): MyEquipmentDAO
    abstract fun noticeDAO(): NoticeDAO


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