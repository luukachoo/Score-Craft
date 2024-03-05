package com.core.data.model

import com.squareup.moshi.Json


data class MatchDto(
    @Json(name = "begin_at")
    val beginAt: String?,
    @Json(name = "detailed_stats")
    val detailedStats: Boolean?,
    val draw: Boolean?,
    @Json(name = "end_at")
    val endAt: String?,
    val forfeit: Boolean?,
    @Json(name = "game_advantage")
    val gameAdvantage: Any?,
    val games: List<GameDto?>?,
    val id: Int?,
    @Json(name = "match_type")
    val matchType: String?,
    @Json(name = "modified_at")
    val modifiedAt: String?,
    val name: String?,
    @Json(name = "number_of_games")
    val numberOfGames: Int?,
    val opponents: List<OpponentDto?>?,
    @Json(name = "original_scheduled_at")
    val originalScheduledAt: String?,
    val rescheduled: Boolean?,
    val results: List<ResultDto?>?,
    @Json(name = "scheduled_at")
    val scheduledAt: String?,
    val slug: String?,
    val status: String?,
    @Json(name = "streams_list")
    val streamsList: List<StreamDto?>?,
    val videogame: VideoGameDto?,
    @Json(name = "videogame_version")
    val winner: TeamDto?,
    @Json(name = "winner_id")
    val winnerId: Int?,
    @Json(name = "winner_type")
    val winnerType: String?
) {
    data class GameDto(
        @Json(name = "begin_at")
        val beginAt: String?,
        val complete: Boolean?,
        @Json(name = "detailed_stats")
        val detailedStats: Boolean?,
        @Json(name = "end_at")
        val endAt: String?,
        val finished: Boolean?,
        val forfeit: Boolean?,
        val id: Int?,
        val length: Int?,
        @Json(name = "match_id")
        val matchId: Int?,
        val position: Int?,
        val status: String?,
        val winner: WinnerDto?,
        @Json(name = "winner_type")
        val winnerType: String?
    ) {
        data class WinnerDto(
            val id: Int?,
            val type: String?
        )
    }

    data class OpponentDto(
        val opponent: TeamDto?,
        val type: String?
    )

    data class ResultDto(
        val score: Int?,
        @Json(name = "team_id")
        val teamId: Int?
    )

    data class StreamDto(
        @Json(name = "embed_url")
        val embedUrl: String?,
        val language: String?,
        val main: Boolean?,
        val official: Boolean?,
        @Json(name = "raw_url")
        val rawUrl: String?
    )
}
