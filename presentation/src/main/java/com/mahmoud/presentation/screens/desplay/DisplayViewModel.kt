package com.mahmoud.presentation.screens.desplay

import com.mahmoud.core.base.BaseViewModel
import com.mahmoud.core.utils.FieldValidator
import com.mahmoud.domain.enums.Gender
import com.mahmoud.domain.models.User
import com.mahmoud.domain.usecases.GetAllUsersUseCase
import com.mahmoud.domain.usecases.InsertUserUseCase
import com.mahmoud.presentation.screens.addUser.AddUserContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(
    private val getUsersUseCase: GetAllUsersUseCase,
) : BaseViewModel<DisplayContract.Event, DisplayContract.State>() {

    override fun setInitialState(): DisplayContract.State = DisplayContract.State()

    override fun handleEvents(event: DisplayContract.Event) {
        when (event) {
            is DisplayContract.Event.LoadUsers -> onLoadUsers()
        }
    }

    init {
        onLoadUsers()
    }

    private fun onLoadUsers() {
        executeFlow(
            dispatcher = Dispatchers.IO,
            execute = { getUsersUseCase() },
        ) { users ->
            setState {
                copy(users = users)
            }
        }
    }

}
