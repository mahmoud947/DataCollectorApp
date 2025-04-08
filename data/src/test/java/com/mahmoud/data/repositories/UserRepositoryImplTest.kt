package com.mahmoud.data.repositories

import com.mahmoud.core.mapper.Mapper
import com.mahmoud.core.mapper.mapAll
import com.mahmoud.data.datasource.local.dao.UserDao
import com.mahmoud.data.datasource.local.entity.UserEntity
import com.mahmoud.data.mapper.UserEntityToDomainMapper
import com.mahmoud.data.mapper.UserMapper
import com.mahmoud.data.mapper.UserToEntityMapper
import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest {

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var userMapper: UserMapper

    @Mock
    private lateinit var userToEntityMapper: UserToEntityMapper

    @Mock
    private lateinit var userEntityToDomainMapper: UserEntityToDomainMapper

    private lateinit var userRepository: UserRepositoryImpl

    @Before
    fun setup() {
        `when`(userMapper.userToEntityMapper).thenReturn(userToEntityMapper)
        `when`(userMapper.userEntityToDomainMapper).thenReturn(userEntityToDomainMapper)

        userRepository = UserRepositoryImpl(userDao, userMapper)
    }

    @Test
    fun `saveUser should convert domain user to entity and save via DAO`() = runTest {
        // Given
        val user = User(name = "mahmoud 1", jobTitle = "Developer", age = 28, gender = Gender.MALE)
        val userEntity = UserEntity(name = "mahmoud 1", jobTitle = "Developer", age = 28, gender = Gender.MALE)
        val expectedId = 42L

        `when`(userToEntityMapper.map(user)).thenReturn(userEntity)
        `when`(userDao.insertUser(userEntity)).thenReturn(expectedId)

        // When
        val resultId = userRepository.saveUser(user)

        // Then
        verify(userMapper.userToEntityMapper).map(user)
        verify(userDao).insertUser(userEntity)
        assertEquals(expectedId, resultId)
    }

    @Test
    fun `getAllUsers should return mapped users from DAO`() = runTest {
        // Given
        val userEntities = listOf(
            UserEntity(id = 1, name = "mahmoud 1", jobTitle = "Developer", age = 28, gender = Gender.MALE),
            UserEntity(id = 2, name = "mahmoud 2", jobTitle = "Developer", age = 28, gender = Gender.MALE)
        )
        val expectedUsers = listOf(
            User(id = 1, name = "mahmoud 1", jobTitle = "Developer", age = 28, gender = Gender.MALE),
            User(id = 2, name = "mahmoud 2", jobTitle = "Developer", age = 28, gender = Gender.MALE)
        )

        `when`(userDao.getAllUsers()).thenReturn(flowOf(userEntities))

        for (i in userEntities.indices) {
            `when`(userEntityToDomainMapper.map(userEntities[i])).thenReturn(expectedUsers[i])
        }

        // When
        val resultFlow = userRepository.getAllUsers()
        val resultUsers = resultFlow.first()

        // Then
        verify(userDao).getAllUsers()
        assertEquals(expectedUsers, resultUsers)
    }
}