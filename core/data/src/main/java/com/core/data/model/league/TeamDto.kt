package com.core.data.model.league

import com.squareup.moshi.Json

data class TeamDto(
    @Json(name = "acronym")
    val acronym: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "location")
    val location: String,
    @Json(name = "name")
    val name: String
)
