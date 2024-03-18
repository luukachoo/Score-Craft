package com.feature.match.event.past_matches

sealed class PastMatchesEvents {
    data object FetchPastMatches : PastMatchesEvents()
    data object ResetErrorMessage : PastMatchesEvents()
}