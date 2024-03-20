package com.core.domain.model.league

data class GetTournamentMatch(
    val id: Int,
    val beginAt: String?,
    val name: String?,
    val winnerId: Int?
)
