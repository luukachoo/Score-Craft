package com.core.data.model.league

import com.squareup.moshi.Json

data class TeamStandingDto(
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "team")
    val team: TeamDto
)
