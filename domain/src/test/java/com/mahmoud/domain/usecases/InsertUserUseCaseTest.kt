package com.mahmoud.domain.usecases

import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import com.mahmoud.domain.repositories.UserRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class InsertUserUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var insertUserUseCase: InsertUserUseCase

    @Before
    fun setup() {
        insertUserUseCase = InsertUserUseCase(repository)
    }

    @Test
    fun `invoke should save user to repository and return user id`() = runTest {
        // Given
        val user = User(name = "mahmoud 1", jobTitle = "Developer", age = 28, gender = Gender.MALE)
        val expectedId = 1L
        `when`(repository.saveUser(user)).thenReturn(expectedId)

        // When
        val result = insertUserUseCase(user)

        // Then
        verify(repository).saveUser(user)
        assertEquals(expectedId, result)
    }
}