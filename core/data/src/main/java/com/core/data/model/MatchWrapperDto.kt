package com.core.data.model

import com.squareup.moshi.Json

data class MatchWrapperDto(
    val match: MatchDto
) {
    data class MatchDto(
        @Json(name = "slug")
        val slug: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "original_scheduled_at")
        val originalScheduledAt: String,
        @Json(name = "games")
        val games: List<GamesDto>,
        @Json(name = "serie_id")
        val serieId: Int,
        @Json(name = "videogame_version")
        val videoGameVersion: String?,
        @Json(name = "number_of_games")
        val numberOfGames: Int,
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "detailed_stats")
        val detailedStats: Boolean,
        @Json(name = "scheduled_at")
        val scheduledAt: String,
        @Json(name = "begin_at")
        val beginAt: String,
        @Json(name = "videogame")
        val videogame: VideoGameDto,
        @Json(name = "results")
        val results: List<TeamDto>,
        @Json(name = "videogame_title")
        val videoGameTitle: String?,
        @Json(name = "forfeit")
        val forfeit: Boolean,
        @Json(name = "opponents")
        val opponents: List<OpponentWithTypeDto>,
        @Json(name = "streams_list")
        val streamsList: List<StreamDto>
    ) {
        data class VideoGameDto(
            val id: Int,
            val name: String,
            val slug: String
        )

        data class WinnerDto(
            @Json(name = "id")
            val id: Int?,
            @Json(name = "type")
            val type: String
        )
    }
}
