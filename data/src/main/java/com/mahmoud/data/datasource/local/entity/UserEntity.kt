package com.mahmoud.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahmoud.domain.enums.Gender


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
)