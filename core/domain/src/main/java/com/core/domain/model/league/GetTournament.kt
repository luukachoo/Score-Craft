package com.core.domain.model.league

import com.core.domain.model.match.GetMatch

data class GetTournament(
    val beginAt: String,
    val id: Int,
    val name: String,
    val prizePool: String,
    val slug: String,
    val matches: List<GetMatch>,
    val teams: List<GetTeam>
)