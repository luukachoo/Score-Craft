package com.core.data.model.auth

data class UserDto(
    val userId: String = "",
    val userName: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val profileImageUri: String = ""
)
