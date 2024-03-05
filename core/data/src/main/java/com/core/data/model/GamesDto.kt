package com.core.data.model

import com.squareup.moshi.Json

data class GamesDto(
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
    val winner: MatchWrapperDto.MatchDto.WinnerDto,
    @Json(name = "winner_type")
    val winnerType: String
)
