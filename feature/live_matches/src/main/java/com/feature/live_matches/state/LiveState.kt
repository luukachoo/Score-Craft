package com.feature.live_matches.state

import com.feature.live_matches.model.Match

data class LiveState(
    val liveMatches: List<Match>? = emptyList(),
    val liveMatchesCount: Int = liveMatches?.size ?: 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
