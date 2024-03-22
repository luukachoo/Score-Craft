package com.example.profile.event

import android.net.Uri
import com.example.profile.model.league.League

sealed class ProfileEvent {
    data object ResetErrorMessage : ProfileEvent()
    data object GetCurrentUser : ProfileEvent()
    data object FetchFavouriteLeagues : ProfileEvent()
    data class UploadProfileImage(val userId: String, val imageUri: Uri) : ProfileEvent()
    data class RemoveFavouriteLeague(val league: League) : ProfileEvent()
    data object LogOut : ProfileEvent()
}