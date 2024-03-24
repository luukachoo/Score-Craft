package com.example.profile.state

import com.example.profile.model.league.League
import com.example.profile.model.user.Users

data class ProfileState(
    val user: Users? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val imageUri: String? = null,
    val userId: String? = null,
    val leagues: List<League>? = emptyList(),
)