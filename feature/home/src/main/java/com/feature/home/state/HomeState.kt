package com.feature.home.state

import com.feature.home.model.League
import com.feature.home.model.auth.Users

data class HomeState(
    val leagues: List<League>? = emptyList(),
    val user: Users? = null,
    val isLoading: Boolean = false,
    val imageUri: String? = null,
    val imageFetched: Boolean = false,
    val errorMessage: String? = null
)
