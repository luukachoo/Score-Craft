package com.feature.live_match_details.state

import com.feature.live_match_details.model.MatchDetails
import com.feature.live_match_details.model.Players

data class LiveMatchDetailsState(
    val players: List<Players> = emptyList(),
    val matchDetails: MatchDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
