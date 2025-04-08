package com.mahmoud.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmoud.data.datasource.constants.DATABASE_NAME
import com.mahmoud.data.datasource.local.dao.UserDao


abstract class CollectorDatabase : RoomDatabase() {
    abstract val categoryDao: UserDao

    companion object {
        private lateinit var INSTANCE: CollectorDatabase
        fun getInstance(context: Context): CollectorDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CollectorDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}