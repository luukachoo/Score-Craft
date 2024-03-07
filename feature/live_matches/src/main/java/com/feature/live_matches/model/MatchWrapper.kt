package com.feature.live_matches.model

import com.core.domain.model.GetMatchWrapper

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
        val videogame: GetMatchWrapper.GetMatch.GetVideoGame,
        val videoGameTitle: Any?,
        val forfeit: Boolean,
        val streamsList: List<Stream>
    ) {
        data class VideoGame(
            val id: Int,
            val name: String,
            val slug: String
        )

        data class Winner(
            val id: Int?,
            val type: String
        )
    }
}