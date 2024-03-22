package com.example.profile.event.profile

import android.net.Uri

sealed class ProfileEvent {
    data object ResetErrorMessage : ProfileEvent()
    data object GetCurrentUser : ProfileEvent()
    data class UploadProfileImage(val userId: String, val imageUri: Uri) : ProfileEvent()
    data object LogOut : ProfileEvent()
}