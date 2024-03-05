package com.core.data.test_models

import com.squareup.moshi.Json


data class MatchDtoListItem(
    @Json(name = "endpoints")
    val endpoints: List<EndpointDto>,
    @Json(name = "match")
    val match: MatchDTO,
    @Json(name = "begin_at")
    val beginAtMatch: String,
    @Json(name = "game_advantage")
    val gameAdvantage: Any?,
    val id: Int,
    @Json(name = "videogame_version")
    val videogameVersion: Any?,
    val name: String,
    val forfeit: Boolean,
    @Json(name = "winner_type")
    val winnerType: String,
    @Json(name = "winner_id")
    val winnerId: Any?,
    @Json(name = "number_of_games")
    val numberOfGames: Int,
    val draw: Boolean,
    @Json(name = "tournament_id")
    val tournamentId: Int,
    @Json(name = "match_type")
    val matchType: String,
    @Json(name = "modified_at")
    val modifiedAt: String,
    val rescheduled: Boolean,
    @Json(name = "end_at")
    val endAt: Any?,
    @Json(name = "detailed_stats")
    val detailedStats: Boolean,
    val winner: Any?,
) {
    data class EndpointDto(
        @Json(name = "begin_at")
        val beginAt: Any?,
        @Json(name = "expected_begin_at")
        val expectedBeginAt: String,
        @Json(name = "last_active")
        val lastActive: Any?,
        @Json(name = "match_id")
        val matchId: Int,
        val open: Boolean,
        val type: String,
        val url: String
    )

    data class MatchDTO(
        val games: List<GameDTO>,
        val league: LeagueDTO,
        @Json(name = "league_id")
        val leagueId: Int,
        val live: LiveDTO,
        val opponents: List<OpponentDTOWrapper.OpponentDTO>,
        @Json(name = "original_scheduled_at")
        val originalScheduledAt: String,
        val results: List<ResultDTO>,
        @Json(name = "scheduled_at")
        val scheduledAt: String,
        val serie: SerieDTO,
        @Json(name = "serie_id")
        val serieId: Int,
        val slug: String,
        val status: String,
        @Json(name = "streams_list")
        val streamsList: List<StreamsDTO>,
        val tournament: TournamentDTO,
        val videogame: VideogameDTO,
        @Json(name = "videogame_title")
        val videogameTitle: VideogameTitleDTO?,
    ) {
        data class GameDTO(
            @Json(name = "begin_at")
            val beginAt: String?,
            val complete: Boolean,
            @Json(name = "detailed_stats")
            val detailedStats: Boolean,
            @Json(name = "end_at")
            val endAt: Any?,
            val finished: Boolean,
            val forfeit: Boolean,
            val id: Int,
            val length: Any?,
            @Json(name = "match_id")
            val matchId: Int,
            val position: Int,
            val status: String,
            val winner: WinnerDTO,
            @Json(name = "winner_type")
            val winnerType: String
        ) {
            data class WinnerDTO(
                @Json(name = "id")
                val id: Any?,
                @Json(name = "type")
                val type: String
            )
        }

        data class LeagueDTO(
            val id: Int,
            @Json(name = "image_url")
            val imageUrl: String?,
            @Json(name = "modified_at")
            val modifiedAt: String?,
            val name: String,
            val slug: String,
            val url: Any?
        )

        data class LiveDTO(
            @Json(name = "opens_at")
            val opensAt: String,
            val supported: Boolean,
            val url: String
        )

        data class OpponentDTOWrapper(
            @Json(name = "opponent")
            val opponent: OpponentDTO,
            @Json(name = "type")
            val type: String
        ) {
            data class OpponentDTO(
                val acronym: String,
                val id: Int,
                @Json(name = "image_url")
                val imageUrl: String,
                val location: String?,
                @Json(name = "modified_at")
                val modifiedAt: String,
                val name: String,
                val slug: String
            )
        }

        data class ResultDTO(
            val score: Int,
            @Json(name = "team_id")
            val teamId: Int
        )

        data class SerieDTO(
            @Json(name = "begin_at")
            val beginAt: String?,
            @Json(name = "end_at")
            val endAt: String?,
            @Json(name = "full_name")
            val fullName: String,
            val id: Int,
            @Json(name = "league_id")
            val leagueId: Int,
            @Json(name = "modified_at")
            val modifiedAt: String,
            val name: String?,
            val season: String?,
            val slug: String,
            @Json(name = "winner_id")
            val winnerId: Any?,
            @Json(name = "winner_type")
            val winnerType: Any?,
            val year: Int
        )

        data class StreamsDTO(
            @Json(name = "embed_url")
            val embedUrl: String,
            val language: String,
            val main: Boolean,
            val official: Boolean,
            @Json(name = "raw_url")
            val rawUrl: String
        )

        data class TournamentDTO(
            @Json(name = "begin_at")
            val beginAt: String?,
            @Json(name = "detailed_stats")
            val detailedStats: Boolean?,
            @Json(name = "end_at")
            val endAt: String?,
            @Json(name = "has_bracket")
            val hasBracket: Boolean,
            val id: Int,
            @Json(name = "league_id")
            val leagueId: Int,
            @Json(name = "live_supported")
            val liveSupported: Boolean,
            @Json(name = "modified_at")
            val modifiedAt: String,
            val name: String,
            val prizepool: Any?,
            @Json(name = "serie_id")
            val serieId: Int,
            val slug: String,
            val tier: String,
            @Json(name = "winner_id")
            val winnerId: Any?,
            @Json(name = "winner_type")
            val winnerType: String
        )

        data class VideogameDTO(
            @Json(name = "id")
            val id: Int,
            @Json(name = "name")
            val name: String,
            @Json(name = "slug")
            val slug: String
        )

        data class VideogameTitleDTO(
            val id: Int,
            val name: String,
            val slug: String,
            @Json(name = "videogame_id")
            val videogameId: Int
        )

    }
}
