package com.mahmoud.domain.usecases

import com.mahmoud.core.base.BaseIOSuspendedUseCase
import com.mahmoud.core.base.BaseISuspendedUseCase
import com.mahmoud.domain.models.User
import com.mahmoud.domain.repositories.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRepository
) : BaseIOSuspendedUseCase<User, Long> {
    override suspend fun invoke(input: User): Long {
        return repository.saveUser(input)
    }
}