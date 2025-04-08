package com.mahmoud.domain.repositories

import com.mahmoud.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: User)
    fun getAllUsers(): Flow<List<User>>
}