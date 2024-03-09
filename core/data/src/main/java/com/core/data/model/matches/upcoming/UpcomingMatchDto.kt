package com.core.data.model.matches.upcoming

import com.core.data.model.matches.live.MatchDetailsDto
import com.core.data.model.matches.live.OpponentWrapperDto
import com.squareup.moshi.Json

data class UpcomingMatchDto(
    @Json(name = "results")
    val results: List<MatchDetailsDto.ResultDto>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "videogame")
    val videogame: VideoGameDto,
    @Json(name = "opponents")
    val opponents: List<OpponentWrapperDto>,
    @Json(name = "begin_at")
    val beginAt: String,
    @Json(name = "name")
    val name: String
)