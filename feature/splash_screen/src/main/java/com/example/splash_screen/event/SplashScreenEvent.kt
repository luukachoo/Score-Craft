package com.example.splash_screen.event

sealed class SplashScreenEvent {
    data object ResetErrorMessage : SplashScreenEvent()
    data object CheckUserSessions : SplashScreenEvent()
}