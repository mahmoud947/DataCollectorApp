package com.mahmoud.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel<Event : UiEvent, State> : ViewModel() {
    private val initialState: UiState<State> by lazy { UiState(setInitialState()) }
    abstract fun setInitialState(): State

    protected val _state: MutableStateFlow<UiState<State>> = MutableStateFlow(initialState)
    val state: MutableStateFlow<UiState<State>> = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<UiSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    lateinit var lastEvent: Event private set

    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun setState(reducer: State.() -> State) {
        _state.update {
            it.copy(status = reducer(it.status))
        }
    }


    fun setEffect(builder: () -> UiSideEffect) {
        val effectValue = builder()
        _effect.trySend(effectValue).isSuccess // Log or handle failure to send effect
    }


    fun launchCoroutine(
        dispatcher: CoroutineDispatcher = Dispatchers.Main, // Default dispatcher is Main
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScopeWithHandler.launch(
            context = dispatcher, // Use the specified dispatcher
            start = start,
            block = block,
        )
    }

    fun <T> executeFlow(dispatcher: CoroutineDispatcher = Dispatchers.IO, execute: () -> Flow<T>, onResult: (T) -> Unit) {
        launchCoroutine(dispatcher) {
            execute()
                .onStart {
                    _state.update { it.copy(isLoading = true) }
                }
                .onCompletion {
                    _state.update { it.copy(isLoading = false) }
                }
                .collectLatest {
                    onResult(it)
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }

    fun <T> execute(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        execute: suspend () -> T,
        onResult: (T) -> Unit
    ) {
        launchCoroutine(dispatcher) {
            _state.update { it.copy(isLoading = true) }
            val result = execute()
            _state.update { it.copy(isLoading = false) }
            onResult(result)
        }
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect { event ->
                lastEvent = event
                handleEvents(event)
            }
        }
    }

    protected abstract fun handleEvents(event: Event)

    protected open fun handleCoroutineException(exception: Throwable) {
        _state.update { it.copy(isLoading = false) }
        // setEffect { ViewSideEffect.Error(exception) }

    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            handleCoroutineException(exception)
        }
    }

    protected val viewModelScopeWithHandler = viewModelScope + coroutineExceptionHandler

}