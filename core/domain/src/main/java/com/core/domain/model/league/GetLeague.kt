package com.core.domain.model.league

data class GetLeague(
    val id: Int,
    val imageUrl: String?,
    val modifiedAt: String,
    val name: String,
    val getSeriesList: List<GetSeries>,
    val slug: String,
    val url: String?,
)