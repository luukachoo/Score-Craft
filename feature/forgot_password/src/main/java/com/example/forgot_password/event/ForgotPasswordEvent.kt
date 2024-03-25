package com.example.forgot_password.event

sealed class ForgotPasswordEvent {
    data class ForgotPassword(val email: String) : ForgotPasswordEvent()
    data object ResetErrorMessage : ForgotPasswordEvent()
    data object OnItemClick : ForgotPasswordEvent()
}