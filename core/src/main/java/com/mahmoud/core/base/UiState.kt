package com.mahmoud.core.base

data class UiState<T>(
    val status: T,
    val isLoading: Boolean = false,
)