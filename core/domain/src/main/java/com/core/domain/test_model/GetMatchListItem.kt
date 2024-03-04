package com.core.domain.test_model

data class GetMatchListItem(
    val match: GetMatch
) {
    data class GetMatch(
        val beginAt: String?,
        val detailedStats: Boolean,
        val endAt: Any?,
        val games: List<GetGame>,
        val id: Int,
        val league: GetLeague,
        val leagueId: Int,
        val matchType: String,
        val name: String,
        val opponents: List<GetOpponentWrapper>,
        val originalScheduledAt: String,
        val rescheduled: Boolean,
        val results: List<GetResult>,
        val scheduledAt: String,
        val slug: String,
        val status: String,
        val streamsList: List<GetStreams>,
        val videogame: GetVideogame,
        val winner: Any?,
        val winnerId: Any?,
        val winnerType: String
    ) {
        data class GetGame(
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
            val winner: GetWinner,
            val winnerType: String
        ) {
            data class GetWinner(
                val id: Int?,
                val type: String
            )
        }

        data class GetLeague(
            val id: Int,
            val imageUrl: String?,
            val modifiedAt: String,
            val name: String,
            val slug: String,
            val url: String?
        )

        data class GetOpponentWrapper(
            val opponent: GetOpponent,
            val type: String
        ) {
            data class GetOpponent(
                val acronym: String,
                val id: Int,
                val imageUrl: String,
                val location: String?,
                val modifiedAt: String,
                val name: String,
                val slug: String
            )
        }

        data class GetResult(
            val score: Int,
            val teamId: Int
        )

        data class GetStreams(
            val embedUrl: String,
            val language: String,
            val main: Boolean,
            val official: Boolean,
            val rawUrl: String
        )

        data class GetVideogame(
            val id: Int,
            val name: String,
            val slug: String
        )
    }
}