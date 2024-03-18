package com.feature.match.state.past_matches

import com.feature.match.model.match.Match

data class PastMatchesState(
    val matches: List<Match> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
