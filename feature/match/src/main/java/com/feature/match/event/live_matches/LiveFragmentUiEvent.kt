package com.feature.match.event.live_matches

sealed interface LiveFragmentUiEvent {
    data class NavigateToDetails(val id: Int) : LiveFragmentUiEvent
}