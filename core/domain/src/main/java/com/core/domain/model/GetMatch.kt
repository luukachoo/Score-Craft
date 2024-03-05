package com.core.domain.model

data class GetMatch(
    val beginAt: String?,
    val detailedStats: Boolean?,
    val draw: Boolean?,
    val endAt: String?,
    val forfeit: Boolean?,
    val gameAdvantage: Any?,
    val games: List<GetGame?>?,
    val id: Int?,
    val matchType: String?,
    val modifiedAt: String?,
    val name: String?,
    val numberOfGames: Int?,
    val opponents: List<GetOpponent?>?,
    val originalScheduledAt: String?,
    val rescheduled: Boolean?,
    val results: List<GetResult?>?,
    val scheduledAt: String?,
    val slug: String?,
    val status: String?,
    val streamsList: List<GetStream?>?,
    val videogame: GetVideoGame?,
    val winner: GetTeam?,
    val winnerId: Int?,
    val winnerType: String?
) {
    data class GetGame(
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
        val winner: GetWinner?,
        val winnerType: String?
    ) {
        data class GetWinner(
            val id: Int?,
            val type: String?
        )
    }

    data class GetOpponent(
        val opponent: GetTeam?,
        val type: String?
    )

    data class GetResult(
        val score: Int?,
        val teamId: Int?
    )

    data class GetStream(
        val embedUrl: String?,
        val language: String?,
        val main: Boolean?,
        val official: Boolean?,
        val rawUrl: String?
    )
}
