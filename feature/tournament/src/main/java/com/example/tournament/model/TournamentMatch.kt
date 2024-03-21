package com.example.tournament.model

import com.core.common.resource.Recyclable

data class TournamentMatch(
    val results: List<Result>,
    val slug: String,
    val winner: Winner,
    val opponents: List<OpponentWrapper>,
    val winnerId: Int,
    val name: String
): Recyclable<TournamentMatch>() {
    override val uniqueValue: TournamentMatch
        get() = this
}