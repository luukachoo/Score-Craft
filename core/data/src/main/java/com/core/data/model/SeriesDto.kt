package com.core.data.model

import com.squareup.moshi.Json

data class SeriesDto(
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
    val winnerId: Int?,
    @Json(name = "winner_type")
    val winnerType: String?,
    @Json(name = "year")
    val year: Int
)