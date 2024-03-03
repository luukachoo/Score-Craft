package com.core.data.model

import com.squareup.moshi.Json


data class MatchDto(
    @Json(name = "begin_at")
    val beginAt: String,
    @Json(name = "detailed_stats")
    val detailedStats: Boolean,
    @Json(name = "draw")
    val draw: Boolean,
    @Json(name = "end_at")
    val endAt: Any?,
    @Json(name = "forfeit")
    val forfeit: Boolean,
    @Json(name = "game_advantage")
    val gameAdvantage: Any?,
    @Json(name = "gameDtoDtos")
    val gameDtoDtos: List<GameDto>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "league")
    val league: LeagueDto,
    @Json(name = "league_id")
    val leagueId: Int,
    @Json(name = "match_type")
    val matchType: String,
    @Json(name = "modified_at")
    val modifiedAt: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "number_of_games")
    val numberOfGames: Int,
    @Json(name = "opponentsDtos")
    val opponentsDtos: List<OpponentDto>,
    @Json(name = "original_scheduled_at")
    val originalScheduledAt: String,
    @Json(name = "rescheduled")
    val rescheduled: Boolean,
    @Json(name = "resultDtos")
    val resultDtos: List<ResultDto>,
    @Json(name = "scheduled_at")
    val scheduledAt: String,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "streams_list")
    val streamsList: List<StreamsDto>,
    @Json(name = "videogame")
    val videogame: VideoGameDto,
    @Json(name = "winner")
    val winner: Any?,
    @Json(name = "winner_id")
    val winnerId: Any?,
    @Json(name = "winner_type")
    val winnerType: String
) {
    data class GameDto(
        @Json(name = "begin_at")
        val beginAt: String,
        @Json(name = "complete")
        val complete: Boolean,
        @Json(name = "detailed_stats")
        val detailedStats: Boolean,
        @Json(name = "end_at")
        val endAt: Any?,
        @Json(name = "finished")
        val finished: Boolean,
        @Json(name = "forfeit")
        val forfeit: Boolean,
        @Json(name = "id")
        val id: Int,
        @Json(name = "length")
        val length: Any?,
        @Json(name = "match_id")
        val matchId: Int,
        @Json(name = "position")
        val position: Int,
        @Json(name = "status")
        val status: String,
        @Json(name = "winner")
        val winner: WinnerDto,
        @Json(name = "winner_type")
        val winnerType: String
    ) {
        data class WinnerDto(
            val id: Any?,
            val type: String
        )
    }

    data class OpponentDto(
        @Json(name = "opponent")
        val opponent: TeamDto,
        @Json(name = "type")
        val type: String
    )

    data class ResultDto(
        @Json(name = "score")
        val score: Int,
        @Json(name = "team_id")
        val teamId: Int
    )

    data class StreamsDto(
        @Json(name = "embed_url")
        val embedUrl: String,
        @Json(name = "language")
        val language: String,
        @Json(name = "main")
        val main: Boolean,
        @Json(name = "official")
        val official: Boolean,
        @Json(name = "raw_url")
        val rawUrl: String
    )
}
