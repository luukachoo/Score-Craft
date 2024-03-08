package com.feature.live_matches.model

data class League(
    val id: Int,
    val imageUrl: String?,
    val modifiedAt: String,
    val name: String,
    val slug: String,
    val url: String?,
    val getSeriesList: List<Series>
)