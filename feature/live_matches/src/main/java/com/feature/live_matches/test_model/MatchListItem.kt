package com.feature.live_matches.test_model

data class MatchListItem(
    val match: Match
) {
    data class Match(
        val beginAt: String,
        val detailedStats: Boolean,
        val endAt: Any?,
        val games: List<Game>,
        val id: Int,
        val league: League,
        val leagueId: Int,
        val matchType: String,
        val name: String,
        val opponents: List<OpponentWrapper>,
        val originalScheduledAt: String,
        val rescheduled: Boolean,
        val results: List<Result>,
        val scheduledAt: String,
        val slug: String,
        val status: String,
        val streamsList: List<Streams>,
        val videogame: Videogame,
        val winner: Any?,
        val winnerId: Any?,
        val winnerType: String
    ) {
        data class Game(
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
            val winner: Winner,
            val winnerType: String
        ) {
            data class Winner(
                val id: Int?,
                val type: String
            )
        }

        data class League(
            val id: Int,
            val imageUrl: String?,
            val modifiedAt: String,
            val name: String,
            val slug: String,
            val url: String?
        )

        data class OpponentWrapper(
            val opponent: Opponent,
            val type: String
        ) {
            data class Opponent(
                val acronym: String,
                val id: Int,
                val imageUrl: String,
                val location: String?,
                val modifiedAt: String,
                val name: String,
                val slug: String
            )
        }

        data class Result(
            val score: Int,
            val teamId: Int
        )

        data class Streams(
            val embedUrl: String,
            val language: String,
            val main: Boolean,
            val official: Boolean,
            val rawUrl: String
        )

        data class Videogame(
            val id: Int,
            val name: String,
            val slug: String
        )
    }
}
