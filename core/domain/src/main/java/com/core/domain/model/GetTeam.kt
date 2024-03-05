package com.core.domain.model

data class GetTeam(
    val score: Int,
    val teamId: Int
)

data class GetResults(
    val firstTeam: GetTeam,
    val secondTeam: GetTeam
)
