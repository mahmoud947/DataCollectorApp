package com.mahmoud.domain.usecases

import com.mahmoud.core.base.BaseOUseCase
import com.mahmoud.domain.models.User
import com.mahmoud.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseOUseCase<Flow<List<User>>> {
    override operator fun invoke(): Flow<List<User>> {
        return repository.getAllUsers()
    }
}