package com.example.chats.mapper

import com.core.domain.model.auth.GetUsers
import com.example.chats.model.Users

fun GetUsers.toPresenter() = Users(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    avatar = avatar
)