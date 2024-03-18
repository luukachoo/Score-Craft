package com.core.data.model.match

import com.squareup.moshi.Json

data class WinnerDto(
    @Json(name = "acronym")
    val acronym: String?,
    @Json(name = "id")
    val winnerId: Int,
    @Json(name = "name")
    val name: String
)
