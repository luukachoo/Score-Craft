package com.feature.live_matches.model

import com.core.domain.model.GetGames
import com.core.domain.model.GetMatchWrapper
import com.core.domain.model.GetOpponentWithType

data class MatchWrapper(
    val match: Match
) {

    data class Match(
        val slug: String,
        val status: String,
        val originalScheduledAt: String,
        val games: List<GetGames>,
        val serieId: Int,
        val videoGameVersion: String?,
        val numberOfGames: Int,
        val id: Int,
        val name: String,
        val detailedStats: Boolean,
        val scheduledAt: String,
        val beginAt: String,
        val videogame: GetMatchWrapper.GetMatch.GetVideoGame,
        val results: List<Team>,
        val videoGameTitle: String?,
        val forfeit: Boolean,
        val opponents: List<GetOpponentWithType>,
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