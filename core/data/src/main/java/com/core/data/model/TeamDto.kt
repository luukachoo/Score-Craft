package com.core.data.model

import com.squareup.moshi.Json

data class TeamDto(
    @Json(name = "score")
    val score: Int,
    @Json(name = "team_id")
    val teamId: Int
)

data class ResultsDto(
    val firstTeam: TeamDto,
    val secondTeam: TeamDto
)