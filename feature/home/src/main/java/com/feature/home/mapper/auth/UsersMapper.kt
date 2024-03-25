package com.feature.home.mapper.auth

import com.core.domain.model.auth.GetUsers
import com.feature.home.model.auth.Users

fun GetUsers.toPresenter() = Users(
    userId = userId,
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    avatar = avatar
)