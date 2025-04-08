package com.mahmoud.di


import com.mahmoud.domain.repositories.UserRepository
import com.mahmoud.domain.usecases.GetAllUsersUseCase
import com.mahmoud.domain.usecases.InsertUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideInsertUserUseCase(
        userRepository: UserRepository
    ): InsertUserUseCase = InsertUserUseCase(repository = userRepository)


    @Provides
    @Singleton
    fun provideGetAllUsersUseCase(
        userRepository: UserRepository
    ): GetAllUsersUseCase = GetAllUsersUseCase(repository = userRepository)
}