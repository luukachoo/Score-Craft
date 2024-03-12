package com.example.login.event

sealed class LoginEvent {
    data class LogIn(val email: String, val password: String) : LoginEvent()
    data object ResetErrorMessage : LoginEvent()
}