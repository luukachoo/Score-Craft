package com.feature.live_matches.model

data class Games(
    val beginAt: String?,
    val complete: Boolean,
    val detailedStats: Boolean,
    val endAt: String?,
    val finished: Boolean,
    val forfeit: Boolean,
    val id: Int,
    val length: Int?,
    val matchId: Int,
    val position: Int,
    val status: String,
    val winner: MatchWrapper.Match.Winner,
    val winnerType: String
)
