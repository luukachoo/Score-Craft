package com.example.message.mapper

import com.core.domain.model.auth.GetUsers
import com.example.message.model.Users

fun GetUsers.toPresenter() = Users(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    avatar = avatar
)