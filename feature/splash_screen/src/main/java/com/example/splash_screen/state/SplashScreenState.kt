package com.example.splash_screen.state

data class SplashScreenState (
    val isLoading: Boolean = false,
    val sessionIsValid: Boolean = false,
    val errorMessage: String? = null
)