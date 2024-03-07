package com.core.domain.model

data class GetMatchDetails(
    val slug: String,
    val status: String,
    val originalScheduledAt: String,
    val id: Int,
    val name: String,
    val detailedStats: Boolean,
    val scheduledAt: String,
    val beginAt: String,
    val opponents: List<GetOpponentWithType>,
    val streamsList: List<GetStream>,
    val results: List<GetResult>
) {
    data class GetResult(
        val score: Int,
        val teamId: Int
    )
}
