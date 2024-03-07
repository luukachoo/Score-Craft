package com.feature.live_matches.event

sealed interface LiveFragmentUiEvent {
    data class NavigateToDetails(val id: Int) : LiveFragmentUiEvent
}