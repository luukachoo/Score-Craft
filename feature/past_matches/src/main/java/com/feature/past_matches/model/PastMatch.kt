package com.feature.past_matches.model

data class PastMatch(
    val results: List<Result>,
    val id: Int,
    val opponents: List<OpponentWrapper>,
    val beginAt: String?,
    val name: String?,
    val winner: Winner,
    val videoGame: VideoGame,
    val winnerId: Int
)