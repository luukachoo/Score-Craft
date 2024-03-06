package com.feature.home.model.auth

data class Users(
    val userId: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
