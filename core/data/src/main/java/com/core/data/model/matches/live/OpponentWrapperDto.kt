package com.core.data.model.matches.live

import com.squareup.moshi.Json

data class OpponentWrapperDto(
    @Json(name = "opponent")
    val opponent: OpponentDto
) {
    data class OpponentDto(
        @Json(name = "id")
        val id: Int,
        @Json(name = "image_url")
        val imageUrl: String?,
        @Json(name = "name")
        val name: String,
        @Json(name = "slug")
        val slug: String,
    )
}
