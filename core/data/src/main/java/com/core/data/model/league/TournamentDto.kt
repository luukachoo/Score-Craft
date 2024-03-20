package com.core.data.model.league

import com.squareup.moshi.Json

data class TournamentDto(
    @Json(name = "slug")
    val slug: String,
    @Json(name = "prizepool")
    val prizePool: String?,
    @Json(name = "begin_at")
    val beginAt: String?,
    @Json(name = "matches")
    val matches: List<TournamentMatchDto>,
    @Json(name = "teams")
    val teams: List<TeamDto>,
    @Json(name = "name")
    val name: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "league")
    val league: LeagueDto
)