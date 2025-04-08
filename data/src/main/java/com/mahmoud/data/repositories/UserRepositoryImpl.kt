package com.mahmoud.data.repositories

import com.mahmoud.core.mapper.mapAll
import com.mahmoud.data.datasource.local.dao.UserDao
import com.mahmoud.data.datasource.local.entity.UserEntity
import com.mahmoud.data.mapper.UserMapper
import com.mahmoud.domain.models.User
import com.mahmoud.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper,
) : UserRepository {

    override suspend fun saveUser(user: User) {
        val userEntity = userMapper.userToEntityMapper.map(user)
        userDao.insertUser(userEntity)
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            userMapper.userEntityToDomainMapper.mapAll(entities)
        }
    }
}