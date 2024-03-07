package com.feature.live_match_details.state

import com.feature.live_match_details.model.MatchDetails

data class LiveMatchDetailsState(
    val players: List<Unit> = emptyList(),
    val matchDetails: MatchDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
