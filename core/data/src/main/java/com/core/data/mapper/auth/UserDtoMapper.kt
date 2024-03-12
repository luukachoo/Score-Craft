package com.core.data.mapper.auth

import com.core.data.model.auth.UserDto
import com.core.domain.model.auth.GetUsers

fun UserDto.toDomain() = GetUsers(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
//    profileImageUri = profileImageUri
)