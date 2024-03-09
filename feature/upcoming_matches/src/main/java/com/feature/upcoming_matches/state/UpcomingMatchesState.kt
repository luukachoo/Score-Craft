package com.feature.upcoming_matches.state

import com.feature.upcoming_matches.model.UpcomingMatch

data class UpcomingMatchesState(
    val upcomingMatches: List<UpcomingMatch> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
