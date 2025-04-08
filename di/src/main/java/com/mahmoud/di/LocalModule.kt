package com.mahmoud.di


import android.content.Context
import com.mahmoud.data.datasource.local.CollectorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideCollectorAppDatabase(@ApplicationContext context: Context): CollectorDatabase =
        CollectorDatabase.getInstance(context)

    @Provides
    fun provideUserDao(database: CollectorDatabase) = database.userDao
}