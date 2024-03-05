package com.feature.live_matches.model

import com.core.domain.model.GetTeam
import com.core.domain.model.GetVideoGame

data class Match(
    val beginAt: String?,
    val detailedStats: Boolean?,
    val draw: Boolean?,
    val endAt: String?,
    val forfeit: Boolean?,
    val gameAdvantage: Any?,
    val games: List<Game?>?,
    val id: Int?,
    val matchType: String?,
    val modifiedAt: String?,
    val name: String?,
    val numberOfGames: Int?,
    val opponents: List<Opponent?>?,
    val originalScheduledAt: String?,
    val rescheduled: Boolean?,
    val results: List<Result?>?,
    val scheduledAt: String?,
    val slug: String?,
    val status: String?,
    val streamsList: List<Stream?>?,
    val videogame: GetVideoGame?,
    val winner: GetTeam?,
    val winnerId: Int?,
    val winnerType: String?
) {
    data class Game(
        val beginAt: String?,
        val complete: Boolean?,
        val detailedStats: Boolean?,
        val endAt: String?,
        val finished: Boolean?,
        val forfeit: Boolean?,
        val id: Int?,
        val length: Int?,
        val matchId: Int?,
        val position: Int?,
        val status: String?,
        val winner: Winner?,
        val winnerType: String?
    ) {
        data class Winner(
            val id: Int?,
            val type: String?
        )
    }

    data class Opponent(
        val opponent: GetTeam?,
        val type: String?
    )

    data class Result(
        val score: Int?,
        val teamId: Int?
    )

    data class Stream(
        val embedUrl: String?,
        val language: String?,
        val main: Boolean?,
        val official: Boolean?,
        val rawUrl: String?
    )
}
