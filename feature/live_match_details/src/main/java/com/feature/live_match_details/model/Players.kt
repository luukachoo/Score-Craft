package com.feature.live_match_details.model

data class Players(
    val firstTeamPlayer: TeamWrapper.Team.Player? = null,
    val secondTeamPlayer: TeamWrapper.Team.Player? = null
)
