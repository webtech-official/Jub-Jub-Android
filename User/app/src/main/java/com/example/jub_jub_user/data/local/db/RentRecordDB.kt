package com.example.jub_jub_user.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jub_jub_user.data.local.dao.RentRecordDAO
import com.example.jub_jub_user.entity.dataclass.RentRecord


@Database(entities = [RentRecord::class], version = 1)
abstract class RentRecordDB: RoomDatabase() {

    abstract fun rentRecordDAO(): RentRecordDAO

    companion object{
        private var INSTANCE: RentRecordDB? = null

        fun getInstance(context: Context): RentRecordDB? {
            if (INSTANCE == null) {
                synchronized(RentRecordDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RentRecordDB::class.java, "rentRecord.db")
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