package com.feature.match.model.match

data class MatchWrapper(
    val match: Match
) {
    data class Match(
        val slug: String,
        val status: String,
        val originalScheduledAt: String,
        val id: Int,
        val name: String,
        val detailedStats: Boolean,
        val scheduledAt: String,
        val beginAt: String,
        val streamsList: List<Stream>
    ) {
        data class Winner(
            val id: Int?,
            val type: String
        )
    }
}