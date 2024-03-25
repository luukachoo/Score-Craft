package com.core.domain.model.league

data class GetSeries(
    val beginAt: String = "",
    val endAt: String? = null,
    val fullName: String = "",
    val id: Int = 0,
    val leagueId: Int = 0,
    val modifiedAt: String = "",
    val name: String? = null,
    val season: String? = null,
    val slug: String = "",
    val winnerId: Any? = null,
    val winnerType: Any? = null,
    val year: Int = 0
)