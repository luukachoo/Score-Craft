package com.core.data.model

import com.squareup.moshi.Json

data class VideoGameDto(
    @Json(name = "current_version")
    val currentVersion: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "slug")
    val slug: String
)