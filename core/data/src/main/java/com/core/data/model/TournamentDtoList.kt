package com.core.data.model

import com.squareup.moshi.Json

data class TournamentDtoList(
    val tournamentDtos: List<TournamentDto>
) {
    data class TournamentDto(
        @Json(name = "begin_at")
        val beginAt: String,
        @Json(name = "detailed_stats")
        val detailedStats: Boolean,
        @Json(name = "end_at")
        val endAt: String,
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
        val prizePool: String,
        @Json(name = "serie_id")
        val serieId: Int,
        @Json(name = "slug")
        val slug: String,
        @Json(name = "tier")
        val tier: String,
        @Json(name = "winner_id")
        val winnerId: Int,
        @Json(name = "winner_type")
        val winnerType: String
    )
}