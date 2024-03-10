package com.core.data.model.matches.past

import com.core.data.model.matches.live.MatchDetailsDto
import com.core.data.model.matches.live.OpponentWrapperDto
import com.core.data.model.matches.upcoming.VideoGameDto
import com.squareup.moshi.Json

data class PastMatchDto(
    @Json(name = "results")
    val results: List<MatchDetailsDto.ResultDto>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "opponents")
    val opponents: List<OpponentWrapperDto>,
    @Json(name = "begin_at")
    val beginAt: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "winner")
    val winner: WinnerDto,
    @Json(name = "videogame")
    val videoGame: VideoGameDto,
    @Json(name = "winner_id")
    val winnerId: Int
)
