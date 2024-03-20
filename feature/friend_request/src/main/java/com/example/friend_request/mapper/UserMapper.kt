package com.example.friend_request.mapper

import com.core.domain.model.auth.GetUsers
import com.example.friend_request.model.Users

fun GetUsers.toPresenter() = Users(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    avatar = avatar
)