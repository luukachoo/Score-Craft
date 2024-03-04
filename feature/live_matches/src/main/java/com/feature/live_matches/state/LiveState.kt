package com.feature.live_matches.state

import com.feature.live_matches.test_model.MatchListItem

data class LiveState(
    val liveMatches: List<MatchListItem.Match>? = emptyList(),
    val liveMatchesCount: Int = liveMatches?.size ?: 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
