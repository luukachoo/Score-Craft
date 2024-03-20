package com.example.tournament.model

data class TournamentMatch(
    val id: Int,
    val beginAt: String?,
    val name: String?,
    val winnerId: Int?
)