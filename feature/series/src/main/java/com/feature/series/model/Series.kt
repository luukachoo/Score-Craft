package com.feature.series.model

import com.core.common.resource.Recyclable

data class Series(
    val beginAt: String,
    val endAt: String?,
    val fullName: String,
    val id: Int,
    val leagueId: Int,
    val modifiedAt: String,
    val name: String?,
    val season: String?,
    val slug: String,
    val winnerId: Any?,
    val winnerType: Any?,
    val year: Int
) : Recyclable<Series>() {
    override val uniqueValue: Series
        get() = this
}
