package com.example.registration.event

sealed class RegisterEvent {
    data class Register(
        val userName: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : RegisterEvent()

    data object ResetErrorMessage : RegisterEvent()
    data object OnBackButtonClick : RegisterEvent()
    data object OnAlreadyRegisteredClick : RegisterEvent()
    data class OnRegisterClick(val email: String, val password: String) : RegisterEvent()
}