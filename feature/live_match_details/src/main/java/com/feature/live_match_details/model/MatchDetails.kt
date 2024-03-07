package com.feature.live_match_details.model


data class MatchDetails(
    val slug: String,
    val status: String,
    val originalScheduledAt: String,
    val id: Int,
    val name: String,
    val detailedStats: Boolean,
    val scheduledAt: String,
    val beginAt: String,
    val opponents: List<OpponentWithType>,
    val streamsList: List<Stream>,
    val results: List<Result>
) {
    data class Result(
        val score: Int,
        val teamId: Int
    )
}
