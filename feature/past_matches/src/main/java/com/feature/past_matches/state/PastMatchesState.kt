package com.feature.past_matches.state

import com.feature.past_matches.model.PastMatch

data class PastMatchesState(
    val pastMatches: List<PastMatch> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
