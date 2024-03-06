package com.core.domain.model.auth

data class GetUsers(
    val userId: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
//    val profileImageUri: String
)
