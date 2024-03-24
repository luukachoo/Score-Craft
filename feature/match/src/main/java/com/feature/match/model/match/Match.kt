package com.feature.match.model.match

import com.core.common.resource.Recyclable

data class Match(
    val results: List<Result>,
    val id: Int,
    val opponents: List<OpponentWrapper>,
    val beginAt: String?,
    val name: String?,
    val winner: Winner?,
    val videoGame: VideoGame,
    val winnerId: Int?
) : Recyclable<Match>() {
    override val uniqueValue: Match
        get() = this
}