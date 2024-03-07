package com.feature.live_match_details.model

data class Players(
    val firstTeamPlayer: OpponentWithType.Opponent.Player,
    val secondTeamPlayer: OpponentWithType.Opponent.Player
)
