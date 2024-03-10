package com.feature.past_matches.event

sealed class PastMatchesEvents {
    data object FetchPastMatches : PastMatchesEvents()
    data object ResetErrorMessage: PastMatchesEvents()
}