package com.feature.match.state.live_matches

import com.feature.match.model.match.MatchWrapper

data class LiveState(
    val liveMatches: List<MatchWrapper.Match>? = emptyList(),
    val liveMatchesCount: Int = liveMatches?.size ?: 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
