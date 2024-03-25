package com.feature.match.state.upcoming_matches

import com.feature.match.model.match.Match

data class UpcomingMatchesState(
    val upcomingMatches: List<Match> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
