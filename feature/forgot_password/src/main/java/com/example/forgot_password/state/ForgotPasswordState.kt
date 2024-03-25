package com.example.forgot_password.state

data class ForgotPasswordState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)