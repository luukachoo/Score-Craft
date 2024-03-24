package com.example.friend_profile.state

import com.example.friend_profile.model.league.League
import com.example.friend_profile.model.user.Users

data class FriendProfileState (
    val friend: Users? = null,
    val leagues: List<League>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)