package com.mahmoud.presentation

import com.mahmoud.core.utils.FieldValidator
import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import com.mahmoud.domain.usecases.InsertUserUseCase
import com.mahmoud.presentation.screens.addUser.AddUserContract
import com.mahmoud.presentation.screens.addUser.AddUserViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddUserViewModelTest {

    @Mock
    private lateinit var fieldValidator: FieldValidator

    @Mock
    private lateinit var insertUserUseCase: InsertUserUseCase

    private lateinit var viewModel: AddUserViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AddUserViewModel(fieldValidator, insertUserUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be empty`() = runTest {
        val initialState = viewModel.state.value

        assertEquals("", initialState.status.name)
        assertEquals("", initialState.status.age)
        assertEquals("", initialState.status.jobTitle)
        assertEquals(Gender.MALE, initialState.status.gender)
        assertEquals(null, initialState.status.nameError)
        assertEquals(null, initialState.status.ageError)
        assertEquals(null, initialState.status.jobTitleError)
        assertEquals(null, initialState.status.genderError)
    }

    @Test
    fun `onNameChanged should update name state`() = runTest {
        // Given
        val testName = "Mahmoud"

        // When
        viewModel.setEvent(AddUserContract.Event.OnNameChanged(testName))
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(testName, viewModel.state.value.status.name)
    }

    @Test
    fun `onAgeChanged should update age state`() = runTest {
        // Given
        val testAge = "30"

        // When
        viewModel.setEvent(AddUserContract.Event.OnAgeChanged(testAge))
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(testAge, viewModel.state.value.status.age)
        assertEquals(30, viewModel.state.value.status.ageAsInt)
    }

    @Test
    fun `onJobTitleChanged should update jobTitle state`() = runTest {
        // Given
        val testJobTitle = "Software Engineer"

        // When
        viewModel.setEvent(AddUserContract.Event.OnJobTitleChanged(testJobTitle))
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(testJobTitle, viewModel.state.value.status.jobTitle)
    }

    @Test
    fun `onGenderChanged should update gender state`() = runTest {
        // Given
        val testGender = Gender.FEMALE

        // When
        viewModel.setEvent(AddUserContract.Event.OnGenderChanged(testGender))
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(testGender, viewModel.state.value.status.gender)
    }

    @Test
    fun `validateForm should show errors when fields are invalid`() = runTest {
        // Given
        val emptyName = ""
        val invalidAge = "a"
        val emptyJobTitle = ""

        `when`(fieldValidator.isNotEmpty(emptyName)).thenReturn(false)
        `when`(fieldValidator.isNotEmpty(emptyJobTitle)).thenReturn(false)
        `when`(fieldValidator.isDigitsOnly(invalidAge)).thenReturn(false)

        // When
        viewModel.setEvent(AddUserContract.Event.OnNameChanged(emptyName))
        viewModel.setEvent(AddUserContract.Event.OnAgeChanged(invalidAge))
        viewModel.setEvent(AddUserContract.Event.OnJobTitleChanged(emptyJobTitle))
        viewModel.setEvent(AddUserContract.Event.OnAddNewUser)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals("This field is required", viewModel.state.value.status.nameError)
        assertEquals("This field is required", viewModel.state.value.status.ageError)
        assertEquals("This field is required", viewModel.state.value.status.jobTitleError)
    }

    @Test
    fun `validateForm should show age error when age is less than 2 digits`() = runTest {
        // Given
        val validName = "Mahmoud"
        val validJobTitle = "Engineer"
        val shortAge = "5"

        `when`(fieldValidator.isNotEmpty(validName)).thenReturn(true)
        `when`(fieldValidator.isNotEmpty(validJobTitle)).thenReturn(true)
        `when`(fieldValidator.isDigitsOnly(shortAge)).thenReturn(true)
        `when`(fieldValidator.isMinLength(shortAge, 2)).thenReturn(false)

        // When
        viewModel.setEvent(AddUserContract.Event.OnNameChanged(validName))
        viewModel.setEvent(AddUserContract.Event.OnAgeChanged(shortAge))
        viewModel.setEvent(AddUserContract.Event.OnJobTitleChanged(validJobTitle))
        viewModel.setEvent(AddUserContract.Event.OnAddNewUser)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(null, viewModel.state.value.status.nameError)
        assertEquals("Age must be at least 2 digits", viewModel.state.value.status.ageError)
        assertEquals(null, viewModel.state.value.status.jobTitleError)
    }

    @Test
    fun `onAddNewUser should insert user and show success message when form is valid`() = runTest {
        // Given
        val validName = "Mahmoud"
        val validAge = "30"
        val validJobTitle = "Engineer"
        val gender = Gender.MALE
        val userId = 1L

        val expectedUser = User(
            name = validName,
            age = 30,
            jobTitle = validJobTitle,
            gender = gender
        )

        `when`(fieldValidator.isNotEmpty(validName)).thenReturn(true)
        `when`(fieldValidator.isNotEmpty(validJobTitle)).thenReturn(true)
        `when`(fieldValidator.isDigitsOnly(validAge)).thenReturn(true)
        `when`(fieldValidator.isMinLength(validAge, 2)).thenReturn(true)
        `when`(insertUserUseCase(expectedUser)).thenReturn(userId)

        // When
        viewModel.setEvent(AddUserContract.Event.OnNameChanged(validName))
        viewModel.setEvent(AddUserContract.Event.OnAgeChanged(validAge))
        viewModel.setEvent(AddUserContract.Event.OnJobTitleChanged(validJobTitle))
        viewModel.setEvent(AddUserContract.Event.OnGenderChanged(gender))
        viewModel.setEvent(AddUserContract.Event.OnAddNewUser)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        verify(insertUserUseCase).invoke(expectedUser)
        
        // Form should be cleared after successful submission
        assertEquals("", viewModel.state.value.status.name)
        assertEquals("", viewModel.state.value.status.age)
        assertEquals("", viewModel.state.value.status.jobTitle)
        
        // Check that success effect was emitted
        val effect = viewModel.effect.first()
        assert(effect is AddUserContract.Effect.ShowSuccessMessage)
        assertEquals("User saved successfully", (effect as AddUserContract.Effect.ShowSuccessMessage).message)
    }
}