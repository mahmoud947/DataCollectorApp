package com.mahmoud.presentation.screens.desplay

import com.mahmoud.core.base.UiEvent
import com.mahmoud.core.base.UiSideEffect
import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User

class DisplayContract {
    sealed class Event : UiEvent {
        data object LoadUsers : Event()
    }

    data class State(
        val users: List<User> = emptyList(),
    )

    sealed interface Effect : UiSideEffect {
    }
}