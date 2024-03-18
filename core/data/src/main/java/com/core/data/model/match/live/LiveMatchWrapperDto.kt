package com.core.data.model.match.live

import com.core.data.model.league.StreamDto
import com.squareup.moshi.Json

data class LiveMatchWrapperDto(
    val match: LiveMatchDto
) {
    data class LiveMatchDto(
        @Json(name = "slug")
        val slug: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "original_scheduled_at")
        val originalScheduledAt: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "detailed_stats")
        val detailedStats: Boolean,
        @Json(name = "scheduled_at")
        val scheduledAt: String,
        @Json(name = "begin_at")
        val beginAt: String,
        @Json(name = "streams_list")
        val streamsList: List<StreamDto>
    ) {
        data class WinnerDto(
            @Json(name = "id")
            val id: Int?,
            @Json(name = "type")
            val type: String
        )
    }
}
