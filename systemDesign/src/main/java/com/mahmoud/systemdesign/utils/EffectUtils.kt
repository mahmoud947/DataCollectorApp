package com.mahmoud.systemdesign.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mahmoud.core.base.UiSideEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
fun Flow<UiSideEffect>.OnEffect(action: (effect: UiSideEffect) -> Unit) {
    LaunchedEffect(Unit) {
        onEach {
            action(it)
        }.collect()
    }
}
