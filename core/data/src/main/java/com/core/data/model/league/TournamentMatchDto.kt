package com.core.data.model.league

import com.squareup.moshi.Json

data class TournamentMatchDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "begin_at")
    val beginAt: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "winner_id")
    val winnerId: Int?
)
