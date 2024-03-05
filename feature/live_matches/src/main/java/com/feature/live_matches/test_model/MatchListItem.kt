package com.feature.live_matches.test_model

data class MatchListItem(
    val endpoints: List<Endpoint>,
    val match: Match,
    val beginAt: String,
) {
    data class Endpoint(
        val beginAt: Any?,
        val expectedBeginAt: String,
        val lastActive: Any?,
        val matchId: Int,
        val open: Boolean,
        val type: String,
        val url: String
    )

    data class Match(
        val detailedStats: Boolean,
        val draw: Boolean,
        val endAt: Any?,
        val forfeit: Boolean,
        val gameAdvantage: Any?,
        val games: List<Game>,
        val id: Int,
        val league: League,
        val leagueId: Int,
        val live: Live,
        val matchType: String,
        val modifiedAt: String,
        val name: String,
        val numberOfGames: Int,
        val opponents: List<OpponentWrapper.Opponent>,
        val rescheduled: Boolean,
        val results: List<Result>,
        val scheduledAt: String,
        val slug: String,
        val status: String,
        val streamsList: List<Streams>,
        val tournamentId: Int,
        val videogame: Videogame,
        val videogameVersion: Any?,
        val winner: Any?,
        val winnerId: Any?,
        val winnerType: String
    ) {
        data class Game(
            val beginAt: String?,
            val complete: Boolean,
            val detailedStats: Boolean,
            val endAt: Any?,
            val finished: Boolean,
            val forfeit: Boolean,
            val id: Int,
            val length: Any?,
            val matchId: Int,
            val position: Int,
            val status: String,
            val winner: Winner,
            val winnerType: String
        ) {
            data class Winner(
                val id: Any?,
                val type: String
            )
        }

        data class League(
            val id: Int,
            val imageUrl: String?,
            val modifiedAt: String?,
            val name: String,
            val slug: String,
            val url: Any?
        )

        data class Live(
            val opensAt: String,
            val supported: Boolean,
            val url: String
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
