package com.feature.live_matches.state

import com.feature.live_matches.model.MatchWrapper

data class LiveState(
    val liveMatches: List<MatchWrapper.Match>? = emptyList(),
    val liveMatchesCount: Int = liveMatches?.size ?: 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
