package com.feature.home.model

data class League(
    val id: Int,
    val imageUrl: String?,
    val modifiedAt: String,
    val name: String,
    val getSeriesList: List<Series>,
    val slug: String,
    val url: Any?,
)
