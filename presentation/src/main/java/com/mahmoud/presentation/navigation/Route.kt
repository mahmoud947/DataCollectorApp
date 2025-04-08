package com.mahmoud.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object CollectorGraph : Route

    @Serializable
    data object AddUser : Route

    @Serializable
    data object DisplayUsers : Route
}