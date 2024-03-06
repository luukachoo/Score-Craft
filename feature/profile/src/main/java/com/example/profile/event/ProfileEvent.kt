package com.example.profile.event

import android.net.Uri

sealed class ProfileEvent {
    data object ResetErrorMessage : ProfileEvent()
    data object GetCurrentUser : ProfileEvent()
    data class UploadProfileImage(val userId: String, val imageUri: Uri) : ProfileEvent()
    data class FetchUserProfileImage(val userId: String): ProfileEvent()
}