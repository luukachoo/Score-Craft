package com.example.profile.event

sealed class ProfileEvent {
    data object ResetErrorMessage : ProfileEvent()
    data object GetCurrentUser : ProfileEvent()
}