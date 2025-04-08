package com.mahmoud.systemdesign.componants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahmoud.core.base.BaseViewModel


@Composable
inline fun <State, Event, reified Vm : BaseViewModel<Event, State>> ComposeRootView(
    withDefaultLoading: Boolean = true,
    loadingView: @Composable () -> Unit = {},
    errorView: @Composable () -> Unit = {},
    content: @Composable (state: State, viewModel: Vm, isLoading: Boolean) -> Unit
) {
    val viewModel = hiltViewModel<Vm>()
    val rootState by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        content(rootState.status, viewModel, rootState.isLoading)
    }

}