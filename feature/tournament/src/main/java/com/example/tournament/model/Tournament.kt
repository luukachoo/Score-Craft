package com.example.tournament.model

import com.core.domain.model.match.GetMatch

data class Tournament(
    val beginAt: String,
    val id: Int,
    val name: String,
    val prizePool: String,
    val slug: String,
    val matches: List<GetMatch>,
    val teams: List<Team>
)