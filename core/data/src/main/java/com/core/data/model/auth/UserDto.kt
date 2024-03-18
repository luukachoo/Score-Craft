package com.core.data.model.auth

data class UserDto(
    var userId: String = "",
    val userName: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val avatar: String = "",
    val fcmToken: String = ""
)
