package com.mahmoud.presentation.screens.addUser

import com.mahmoud.core.base.UiEvent
import com.mahmoud.core.base.UiSideEffect
import com.mahmoud.domain.enums.Gender

class AddUserContract {
    sealed class Event : UiEvent {
        data class OnNameChanged(val name: String) : Event()
        data class OnAgeChanged(val age: String) : Event()
        data class OnJobTitleChanged(val jobTitle: String) : Event()
        data class OnGenderChanged(val gender: Gender) : Event()
        object OnAddNewUser : Event()
    }

    data class State(
        val name: String = "",
        val nameError: String? = null,
        val age: String = "",
        val ageError: String? = null,
        val jobTitle: String = "",
        val jobTitleError: String? = null,
        val ageAsInt: Int = 0,
        val gender: Gender = Gender.MALE,
        val genderError: String? = null,
        val isFormValid: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface Effect : UiSideEffect {
        data class ShowSuccessMessage(val message: String) : Effect
    }
}