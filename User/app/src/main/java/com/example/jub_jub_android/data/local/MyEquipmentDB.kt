package com.example.jub_jub_android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_android.entity.dataclass.MyEquipment


@Database(entities = [MyEquipment::class], version = 2, exportSchema = false)
abstract class MyEquipmentDB: RoomDatabase() {

    abstract fun myEquipmentDAO() : MyEquipmentDAO

    companion object {
        private var INSTANCE: MyEquipmentDB? = null

        fun getInstance(context: Context): MyEquipmentDB? {
            if (INSTANCE == null) {
                synchronized(MyEquipmentDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MyEquipmentDB::class.java, "myEquipment.db")
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