package com.core.domain.model.league

data class GetLeague(
    val id: Int = 0,
    val imageUrl: String? = null,
    val modifiedAt: String = "",
    val name: String = "",
    val getSeriesList: List<GetSeries> = emptyList(),
    val slug: String = "",
    val url: Any? = null
)