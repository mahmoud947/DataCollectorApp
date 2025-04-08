package com.mahmoud.domain.models

import com.mahmoud.domain.enums.Gender

data class User(
    val id: Int = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender
)