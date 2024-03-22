package com.example.tournament.model

import com.core.common.resource.Recyclable

data class TournamentMatch(
    val results: List<Result>,
    val slug: String,
    val opponents: List<OpponentWrapper>,
    val name: String
): Recyclable<TournamentMatch>() {
    override val uniqueValue: TournamentMatch
        get() = this
}