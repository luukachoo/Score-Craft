package com.feature.live_match_details.state

import com.feature.live_match_details.model.MatchDetails
import com.feature.live_match_details.model.Players

data class MatchDetailsViewState(
    val players: List<Players> = emptyList(),
    val match: MatchDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
