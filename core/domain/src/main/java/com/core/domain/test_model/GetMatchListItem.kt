package com.core.domain.test_model

//data class GetMatchListItem(
//    val endpoints: List<GetEndpoint>,
//    val match: GetMatch,
//    val beginAt: String,
//) {
//
//    data class GetEndpoint(
//        val beginAt: Any?,
//        val expectedBeginAt: String,
//        val lastActive: Any?,
//        val matchId: Int,
//        val open: Boolean,
//        val type: String,
//        val url: String
//    )
//    data class GetMatch(
//        val detailedStats: Boolean,
//        val draw: Boolean,
//        val endAt: Any?,
//        val forfeit: Boolean,
//        val gameAdvantage: Any?,
//        val games: List<GetGame>,
//        val id: Int,
//        val league: GetLeague,
//        val leagueId: Int,
//        val live: GetLive,
//        val matchType: String,
//        val modifiedAt: String,
//        val name: String,
//        val numberOfGames: Int,
//        val opponents: List<GetOpponentWrapper.GetOpponent>,
//        val rescheduled: Boolean,
//        val results: List<GetResult>,
//        val scheduledAt: String,
//        val slug: String,
//        val status: String,
//        val streamsList: List<GetStreams>,
//        val tournamentId: Int,
//        val videogame: GetVideogame,
//        val videogameVersion: Any?,
//        val winner: Any?,
//        val winnerId: Any?,
//        val winnerType: String
//    ) {
//        data class GetGame(
//            val beginAt: String?,
//            val complete: Boolean,
//            val detailedStats: Boolean,
//            val endAt: Any?,
//            val finished: Boolean,
//            val forfeit: Boolean,
//            val id: Int,
//            val length: Any?,
//            val matchId: Int,
//            val position: Int,
//            val status: String,
//            val winner: GetWinner,
//            val winnerType: String
//        ) {
//            data class GetWinner(
//                val id: Any?,
//                val type: String
//            )
//        }
//
//        data class GetLeague(
//            val id: Int,
//            val imageUrl: String?,
//            val modifiedAt: String?,
//            val name: String,
//            val slug: String,
//            val url: Any?
//        )
//
//        data class GetLive(
//            val opensAt: String,
//            val supported: Boolean,
//            val url: String
//        )
//
//        data class GetOpponentWrapper(
//            val opponent: GetOpponent,
//            val type: String
//        ) {
//            data class GetOpponent(
//                val acronym: String,
//                val id: Int,
//                val imageUrl: String,
//                val location: String?,
//                val modifiedAt: String,
//                val name: String,
//                val slug: String
//            )
//        }
//
//        data class GetResult(
//            val score: Int,
//            val teamId: Int
//        )
//
//        data class GetStreams(
//            val embedUrl: String,
//            val language: String,
//            val main: Boolean,
//            val official: Boolean,
//            val rawUrl: String
//        )
//
//        data class GetVideogame(
//            val id: Int,
//            val name: String,
//            val slug: String
//        )
//    }
//}