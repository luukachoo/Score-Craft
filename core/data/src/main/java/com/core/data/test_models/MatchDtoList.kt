package com.core.data.test_models

import com.squareup.moshi.Json


data class MatchDtoListItem(
    @Json(name = "endpoints")
    val endpoints: List<EndpointDto>,
    @Json(name = "match")
    val match: MatchDTO
) {
    data class EndpointDto(
        @Json(name = "begin_at")
        val beginAt: String?,
        @Json(name = "expected_begin_at")
        val expectedBeginAt: String,
        @Json(name = "last_active")
        val lastActive: Any?,
        @Json(name = "match_id")
        val matchId: Int,
        @Json(name = "open")
        val open: Boolean,
        @Json(name = "type")
        val type: String,
        @Json(name = "url")
        val url: String
    )

    data class MatchDTO(
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
        @Json(name = "games")
        val games: List<GameDTO>,
        @Json(name = "id")
        val id: Int,
        @Json(name = "league")
        val league: LeagueDTO,
        @Json(name = "league_id")
        val leagueId: Int,
        @Json(name = "live")
        val live: LiveDTO,
        @Json(name = "match_type")
        val matchType: String,
        @Json(name = "modified_at")
        val modifiedAt: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "number_of_games")
        val numberOfGames: Int,
        @Json(name = "opponents")
        val opponents: List<OpponentDTOWrapper>,
        @Json(name = "original_scheduled_at")
        val originalScheduledAt: String,
        @Json(name = "rescheduled")
        val rescheduled: Boolean,
        @Json(name = "results")
        val results: List<ResultDTO>,
        @Json(name = "scheduled_at")
        val scheduledAt: String,
        @Json(name = "serie")
        val serie: SerieDTO,
        @Json(name = "serie_id")
        val serieId: Int,
        @Json(name = "slug")
        val slug: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "streams_list")
        val streamsList: List<StreamsDTO>,
        @Json(name = "tournament")
        val tournament: TournamentDTO,
        @Json(name = "tournament_id")
        val tournamentId: Int,
        @Json(name = "videogame")
        val videogame: VideogameDTO,
        @Json(name = "videogame_title")
        val videogameTitle: VideogameTitleDTO?,
        @Json(name = "videogame_version")
        val videogameVersion: VideogameVersionDTO?,
        @Json(name = "winner")
        val winner: Any?,
        @Json(name = "winner_id")
        val winnerId: Any?,
        @Json(name = "winner_type")
        val winnerType: String
    ) {
        data class GameDTO(
            @Json(name = "begin_at")
            val beginAt: String?,
            @Json(name = "complete")
            val complete: Boolean,
            @Json(name = "detailed_stats")
            val detailedStats: Boolean,
            @Json(name = "end_at")
            val endAt: String?,
            @Json(name = "finished")
            val finished: Boolean,
            @Json(name = "forfeit")
            val forfeit: Boolean,
            @Json(name = "id")
            val id: Int,
            @Json(name = "length")
            val length: Int?,
            @Json(name = "match_id")
            val matchId: Int,
            @Json(name = "position")
            val position: Int,
            @Json(name = "status")
            val status: String,
            @Json(name = "winner")
            val winner: WinnerDTO,
            @Json(name = "winner_type")
            val winnerType: String
        ) {
            data class WinnerDTO(
                @Json(name = "id")
                val id: Int?,
                @Json(name = "type")
                val type: String
            )
        }

        data class LeagueDTO(
            @Json(name = "id")
            val id: Int,
            @Json(name = "image_url")
            val imageUrl: String?,
            @Json(name = "modified_at")
            val modifiedAt: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "slug")
            val slug: String,
            @Json(name = "url")
            val url: String?
        )

        data class LiveDTO(
            @Json(name = "opens_at")
            val opensAt: String,
            @Json(name = "supported")
            val supported: Boolean,
            @Json(name = "url")
            val url: String
        )

        data class OpponentDTOWrapper(
            @Json(name = "opponent")
            val opponent: OpponentDTO,
            @Json(name = "type")
            val type: String
        ) {
            data class OpponentDTO(
                @Json(name = "acronym")
                val acronym: String,
                @Json(name = "id")
                val id: Int,
                @Json(name = "image_url")
                val imageUrl: String,
                @Json(name = "location")
                val location: String?,
                @Json(name = "modified_at")
                val modifiedAt: String,
                @Json(name = "name")
                val name: String,
                @Json(name = "slug")
                val slug: String
            )
        }

        data class ResultDTO(
            @Json(name = "score")
            val score: Int,
            @Json(name = "team_id")
            val teamId: Int
        )

        data class SerieDTO(
            @Json(name = "begin_at")
            val beginAt: String,
            @Json(name = "end_at")
            val endAt: String?,
            @Json(name = "full_name")
            val fullName: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "league_id")
            val leagueId: Int,
            @Json(name = "modified_at")
            val modifiedAt: String,
            @Json(name = "name")
            val name: String?,
            @Json(name = "season")
            val season: String?,
            @Json(name = "slug")
            val slug: String,
            @Json(name = "winner_id")
            val winnerId: Any?,
            @Json(name = "winner_type")
            val winnerType: Any?,
            @Json(name = "year")
            val year: Int
        )

        data class StreamsDTO(
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

        data class TournamentDTO(
            @Json(name = "begin_at")
            val beginAt: String,
            @Json(name = "detailed_stats")
            val detailedStats: Boolean,
            @Json(name = "end_at")
            val endAt: String?,
            @Json(name = "has_bracket")
            val hasBracket: Boolean,
            @Json(name = "id")
            val id: Int,
            @Json(name = "league_id")
            val leagueId: Int,
            @Json(name = "live_supported")
            val liveSupported: Boolean,
            @Json(name = "modified_at")
            val modifiedAt: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "prizepool")
            val prizepool: Any?,
            @Json(name = "serie_id")
            val serieId: Int,
            @Json(name = "slug")
            val slug: String,
            @Json(name = "tier")
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
            @Json(name = "id")
            val id: Int,
            @Json(name = "name")
            val name: String,
            @Json(name = "slug")
            val slug: String,
            @Json(name = "videogame_id")
            val videogameId: Int
        )

        data class VideogameVersionDTO(
            @Json(name = "current")
            val current: Boolean,
            @Json(name = "name")
            val name: String
        )
    }
}
