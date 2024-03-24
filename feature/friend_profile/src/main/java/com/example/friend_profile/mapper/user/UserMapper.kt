package com.example.friend_profile.mapper.user

import com.core.domain.model.auth.GetUsers
import com.example.friend_profile.model.user.Users

fun GetUsers.toDomain() = Users(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    avatar = avatar
)