package com.mahmoud.presentation.screens.addUser

import com.mahmoud.core.base.BaseViewModel
import com.mahmoud.core.utils.FieldValidator
import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import com.mahmoud.domain.usecases.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val fieldValidator: FieldValidator,
    private val insertUserUseCase: InsertUserUseCase,
) : BaseViewModel<AddUserContract.Event, AddUserContract.State>() {

    override fun setInitialState(): AddUserContract.State = AddUserContract.State()

    override fun handleEvents(event: AddUserContract.Event) {
        when (event) {
            is AddUserContract.Event.OnNameChanged -> onNameChanged(event.name)
            is AddUserContract.Event.OnAgeChanged -> onAgeChanged(event.age)
            is AddUserContract.Event.OnJobTitleChanged -> onJobTitleChanged(event.jobTitle)
            is AddUserContract.Event.OnGenderChanged -> onGenderChanged(event.gender)
            is AddUserContract.Event.OnAddNewUser -> validateForm(::onAddNewUser)
        }
    }


    private fun onAddNewUser() {
        execute(
            dispatcher = Dispatchers.IO,
            execute = {
                insertUserUseCase(
                    User(
                        name = state.value.status.name,
                        age = state.value.status.ageAsInt,
                        jobTitle = state.value.status.jobTitle,
                        gender = state.value.status.gender
                    )
                )
            }
        ) { id ->
            setEffect {
                AddUserContract.Effect.ShowSuccessMessage("User saved successfully")
            }
            clearForm()
        }
    }

    private inline fun validateForm(onValid: () -> Unit) {
        setState {
            copy(
                nameError = null,
                ageError = null,
                jobTitleError = null,
                genderError = null
            )
        }
        val nameResult =
            if (!fieldValidator.isNotEmpty(state.value.status.name)) "This field is required" else null
        val jopTitleResult =
            if (!fieldValidator.isNotEmpty(state.value.status.jobTitle)) "This field is required" else null
        val ageResult =
            if (!fieldValidator.isDigitsOnly(state.value.status.age)) {
                "This field is required"
            } else if (!fieldValidator.isMinLength(state.value.status.age, 2)) {
                "Age must be at least 2 digits"
            } else {
                null
            }


        val hasError = listOf(
            nameResult,
            ageResult,
            jopTitleResult
        ).any { it != null }

        if (hasError) {
            setState {
                copy(
                    nameError = nameResult,
                    ageError = ageResult,
                    jobTitleError = jopTitleResult
                )
            }
        } else {
            onValid()
        }
    }


    private fun onGenderChanged(gender: Gender) {
        setState {
            copy(gender = gender)
        }
    }

    private fun onAgeChanged(age: String) {
        setState {
            copy(age = age, ageAsInt = age.toIntOrNull() ?: 0)
        }
    }

    private fun onNameChanged(name: String) {
        setState {
            copy(name = name)
        }
    }

    private fun onJobTitleChanged(jobTitle: String) {
        setState {
            copy(jobTitle = jobTitle)
        }
    }

    private fun clearForm() {
        setState {
            copy(
                name = "",
                age = "",
                jobTitle = "",
                gender = Gender.MALE
            )
        }
    }

}