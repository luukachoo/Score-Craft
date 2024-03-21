package com.example.tournament.model

import com.core.common.resource.Recyclable

data class Tournament(
    val beginAt: String,
    val id: Int,
    val name: String,
    val prizePool: String,
    val slug: String,
    val matches: List<TournamentMatch>,
    val teams: List<Team>,
    val league: League
) : Recyclable<Tournament>() {
    override val uniqueValue: Tournament
        get() = this
}