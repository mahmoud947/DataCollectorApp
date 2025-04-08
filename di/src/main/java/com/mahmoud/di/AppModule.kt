package com.mahmoud.di

import com.mahmoud.core.utils.DefaultFieldValidator
import com.mahmoud.core.utils.FieldValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFieldValidator(): FieldValidator = DefaultFieldValidator()
}