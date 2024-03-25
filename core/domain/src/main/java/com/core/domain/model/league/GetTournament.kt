package com.core.domain.model.league

data class GetTournament(
    val beginAt: String,
    val id: Int,
    val name: String,
    val prizePool: String,
    val slug: String,
    val teams: List<GetTeam>,
    val league: GetLeague
)