package com.feature.live_matches.event

sealed class LivesFragmentEvent {
    data object FetchLiveMatches : LivesFragmentEvent()
    data object ResetErrorMessage : LivesFragmentEvent()
    data class ItemClick(val id: Int) : LivesFragmentEvent()
}