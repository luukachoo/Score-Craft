package com.example.profile.mapper.auth

import com.core.domain.model.auth.GetUsers
import com.example.profile.model.Users

fun GetUsers.toPresenter() = Users(
    userName = userName,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)