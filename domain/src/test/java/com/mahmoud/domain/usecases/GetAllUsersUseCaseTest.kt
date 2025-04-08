package com.mahmoud.domain.usecases

import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import com.mahmoud.domain.repositories.UserRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllUsersUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var getAllUsersUseCase: GetAllUsersUseCase

    @Before
    fun setup() {
        getAllUsersUseCase = GetAllUsersUseCase(repository)
    }

    @Test
    fun `invoke should return all users from repository`() = runTest {
        // Given
        val users = listOf(
            User(id = 1, name = "mahmoud 1", jobTitle = "Developer", age = 28, gender = Gender.MALE),
            User(id = 2, name = "mahmoud 2", jobTitle = "Developer", age = 28, gender = Gender.MALE)
        )
        val flowUsers: Flow<List<User>> = flowOf(users)
        `when`(repository.getAllUsers()).thenReturn(flowUsers)

        // When
        val result = getAllUsersUseCase()

        // Then
        verify(repository).getAllUsers()
        assertEquals(flowUsers, result)
    }
}
