package com.core.data.model.match

import com.core.data.model.match.live.LiveMatchDetailsDto
import com.squareup.moshi.Json

data class MatchDto(
    @Json(name = "results")
    val results: List<LiveMatchDetailsDto.ResultDto>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "opponents")
    val opponents: List<OpponentWrapperDto>,
    @Json(name = "begin_at")
    val beginAt: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "winner")
    val winner: WinnerDto?,
    @Json(name = "videogame")
    val videoGame: VideoGameDto,
    @Json(name = "winner_id")
    val winnerId: Int?
)
