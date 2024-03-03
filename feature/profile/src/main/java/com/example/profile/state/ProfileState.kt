package com.example.profile.state

import com.example.profile.model.Users

data class ProfileState (
    val user: Users? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)