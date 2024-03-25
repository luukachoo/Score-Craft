package com.feature.live_match_details.event

sealed interface LiveMatchDetailsUiEvent {
    data object NavigateToLives : LiveMatchDetailsUiEvent
}