package com.mahmoud.presentation.screens.desplay

import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import com.mahmoud.domain.usecases.GetAllUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import junit.framework.TestCase.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DisplayViewModelTest {

    @Mock
    private lateinit var getUsersUseCase: GetAllUsersUseCase

    private lateinit var viewModel: DisplayViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DisplayViewModel(getUsersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be empty list of users`() = runTest {
        // Given
        val emptyList = emptyList<User>()
        `when`(getUsersUseCase()).thenReturn(flowOf(emptyList))

        // When
        viewModel = DisplayViewModel(getUsersUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(emptyList, viewModel.state.value.status.users)
    }

    @Test
    fun `onLoadUsers should update state with user list`() = runTest {
        // Given
        val mockUsers = listOf(
            User(id = 1, name = "Mahmoud", jobTitle = "Software Engineer", age = 30, gender = Gender.MALE),
            User(id = 2, name = "Mahmoud 2", jobTitle = "Software Engineer", age = 30, gender = Gender.MALE)
        )
        `when`(getUsersUseCase()).thenReturn(flowOf(mockUsers))

        // When
        viewModel = DisplayViewModel(getUsersUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(mockUsers, viewModel.state.value.status.users)
    }

    @Test
    fun `LoadUsers event should trigger loading of users`() = runTest {
        // Given
        val mockUsers = listOf(
            User(id = 1, name = "Mahmoud", jobTitle = "Software Engineer", age = 30, gender = Gender.MALE),
            User(id = 2, name = "Mahmoud 2", jobTitle = "Software Engineer", age = 30, gender = Gender.MALE)
        )
        `when`(getUsersUseCase()).thenReturn(flowOf(mockUsers))

        // When
        viewModel.setEvent(DisplayContract.Event.LoadUsers)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(mockUsers, viewModel.state.value.status.users)
    }
}