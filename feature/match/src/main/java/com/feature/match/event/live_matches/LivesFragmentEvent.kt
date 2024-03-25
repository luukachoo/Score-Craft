package com.feature.match.event.live_matches

sealed class LivesFragmentEvent {
    data object FetchLiveMatches : LivesFragmentEvent()
    data object ResetErrorMessage : LivesFragmentEvent()
    data class ItemClick(val id: Int) : LivesFragmentEvent()
}