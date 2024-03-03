package com.core.domain.model.auth

data class GetUsers(
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
