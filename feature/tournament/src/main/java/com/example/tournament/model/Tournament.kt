package com.example.tournament.model

data class Tournament(
    val beginAt: String,
    val id: Int,
    val name: String,
    val prizePool: String,
    val slug: String,
    val matches: List<TournamentMatch>,
    val teams: List<Team>,
    val league: League
)