//package com.core.domain.model
//
//data class GetMatch(
//    val endAt: Any?,
//    val gameDtos: List<GetGame>,
//    val id: Int,
//    val league: GetLeague,
//    val leagueId: Int,
//    val matchType: String,
//    val modifiedAt: String,
//    val name: String,
//    val numberOfGames: Int,
//    val getOpponents: List<GetOpponent>,
//    val originalScheduledAt: String,
//    val rescheduled: Boolean,
//    val getResults: List<GetResult>,
//    val scheduledAt: String,
//    val slug: String,
//    val status: String,
//    val streamsList: List<GetStreams>,
//    val videogame: GetVideoGame,
//    val winner: Any?,
//    val winnerId: Any?,
//    val winnerType: String
//) {
//    data class GetGame(
//        val beginAt: String,
//        val complete: Boolean,
//        val detailedStats: Boolean,
//        val endAt: Any?,
//        val finished: Boolean,
//        val id: Int,
//        val matchId: Int,
//        val position: Int,
//        val status: String,
//        val winner: GetWinner,
//        val winnerType: String
//    ) {
//        data class GetWinner(
//            val id: Any?,
//            val type: String
//        )
//    }
//
//    data class GetOpponent(
//        val opponent: GetTeam,
//        val type: String
//    )
//
//    data class GetResult(
//        val score: Int,
//        val teamId: Int
//    )
//
//    data class GetStreams(
//        val embedUrl: String,
//        val language: String,
//        val main: Boolean,
//        val official: Boolean,
//        val rawUrl: String
//    )
//}
