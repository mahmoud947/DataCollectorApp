package com.mahmoud.data.mapper

import com.mahmoud.core.mapper.Mapper
import com.mahmoud.data.datasource.local.entity.UserEntity
import com.mahmoud.domain.models.User


object UserEntityToDomainMapper : Mapper<UserEntity, User> {
    override fun map(from: UserEntity): User =
        User(
            id = from.id,
            name = from.name,
            age = from.age,
            jobTitle = from.jobTitle,
            gender = from.gender
        )
}

object UserToEntityMapper : Mapper<User, UserEntity>{
    override fun map(from: User): UserEntity {
        return UserEntity(
            id = from.id,
            name = from.name,
            age = from.age,
            jobTitle = from.jobTitle,
            gender = from.gender
        )
    }
}