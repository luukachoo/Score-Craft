package com.core.data.model.live_matches

import com.squareup.moshi.Json

data class TeamWrapperDto(
    @Json(name = "opponents")
    val opponents: List<TeamDto>
) {
    data class TeamDto(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "slug")
        val slug: String,
        @Json(name = "players")
        val players: List<PlayerDto>
    ) {
        data class PlayerDto(
            @Json(name = "active")
            val active: Boolean,
            @Json(name = "age")
            val age: Int?,
            @Json(name = "birthday")
            val birthday: String?,
            @Json(name = "first_name")
            val firstName: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "image_url")
            val imageUrl: String?,
            @Json(name = "last_name")
            val lastName: String,
            @Json(name = "modified_at")
            val modifiedAt: String,
            @Json(name = "name")
            val name: String,
            @Json(name = "nationality")
            val nationality: String,
            @Json(name = "role")
            val role: String?,
            @Json(name = "slug")
            val slug: String
        )
    }
}
