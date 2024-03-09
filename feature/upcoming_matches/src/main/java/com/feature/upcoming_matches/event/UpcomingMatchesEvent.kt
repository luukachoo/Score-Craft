package com.feature.upcoming_matches.event

sealed class UpcomingMatchesEvent {
    data object FetchUpcomingMatches : UpcomingMatchesEvent()
    data object ResetErrorMessage : UpcomingMatchesEvent()
}