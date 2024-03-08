package com.core.data.model

import com.squareup.moshi.Json

data class LeagueDtoHomeFeature(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "modified_at")
    val modifiedAt: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "url")
    val url: String?,
    @Json(name = "series")
    val series: List<SeriesDto>
)