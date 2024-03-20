package com.feature.match.event.upcoming_matches

sealed class UpcomingMatchesEvent {
    data object FetchUpcomingMatches : UpcomingMatchesEvent()
    data object ResetErrorMessage : UpcomingMatchesEvent()
}