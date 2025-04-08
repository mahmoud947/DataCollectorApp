package com.mahmoud.di;

import com.mahmoud.data.repositories.UserRepositoryImpl;
import com.mahmoud.domain.repositories.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}