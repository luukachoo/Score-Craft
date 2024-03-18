package com.core.data.model.league

import com.squareup.moshi.Json

data class StreamDto(
    @Json(name = "embed_url")
    val embedUrl: String?,
    @Json(name = "language")
    val language: String,
    @Json(name = "main")
    val main: Boolean,
    @Json(name = "official")
    val official: Boolean,
    @Json(name = "raw_url")
    val rawUrl: String
)
