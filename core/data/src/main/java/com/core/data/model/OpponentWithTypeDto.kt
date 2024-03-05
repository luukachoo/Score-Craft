package com.core.data.model

import com.squareup.moshi.Json

data class OpponentWithTypeDto(
    @Json(name = "opponent")
    val opponent: OpponentDto,
    @Json(name = "type")
    val type: String
) {
    data class OpponentDto(
        @Json(name = "acronym")
        val acronym: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "image_url")
        val imageUrl: String,
        @Json(name = "location")
        val location: String?,
        @Json(name = "modified_at")
        val modifiedAt: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "slug")
        val slug: String
    )
}
