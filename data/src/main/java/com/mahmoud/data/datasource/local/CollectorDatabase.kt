package com.mahmoud.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmoud.data.datasource.constants.DATABASE_NAME
import com.mahmoud.data.datasource.local.dao.UserDao
import com.mahmoud.data.datasource.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class CollectorDatabase : RoomDatabase() {
    abstract val userDao: UserDao

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