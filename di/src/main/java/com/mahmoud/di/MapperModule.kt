package com.mahmoud.di

import com.mahmoud.data.mapper.UserEntityToDomainMapper
import com.mahmoud.data.mapper.UserMapper
import com.mahmoud.data.mapper.UserToEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper = UserMapper(
        userEntityToDomainMapper = UserEntityToDomainMapper(),
        userToEntityMapper = UserToEntityMapper()
    )

}