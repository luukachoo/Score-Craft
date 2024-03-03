package com.feature.home.state

import com.feature.home.model.League

data class HomeState(
    val categories: List<League>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)