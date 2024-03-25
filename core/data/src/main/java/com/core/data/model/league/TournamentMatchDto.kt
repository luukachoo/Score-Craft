package com.core.data.model.league

import com.core.data.model.match.OpponentWrapperDto
import com.squareup.moshi.Json

data class TournamentMatchDto(
    @Json(name = "results")
    val results: List<ResultDto>,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "opponents")
    val opponents: List<OpponentWrapperDto>,
    @Json(name = "name")
    val name: String
)