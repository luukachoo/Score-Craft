package com.example.profile.mapper.auth

import com.core.domain.model.auth.GetUsers
import com.example.profile.model.user.Users

fun GetUsers.toPresenter() = Users(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    avatar = avatar
)