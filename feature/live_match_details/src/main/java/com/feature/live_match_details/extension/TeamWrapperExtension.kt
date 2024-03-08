package com.feature.live_match_details.extension

import com.feature.live_match_details.model.Players
import com.feature.live_match_details.model.TeamWrapper

fun TeamWrapper.toPlayersModel(): List<Players> {
    val players = mutableListOf<Players>()

    val team1PlayersList = opponents.firstOrNull()?.players ?: emptyList()
    val team2PlayersList = opponents.lastOrNull()?.players ?: emptyList()

    val maxPlayers = maxOf(team1PlayersList.size, team2PlayersList.size)

    for (i in 0 until maxPlayers) {
        val team1Player = team1PlayersList.getOrNull(i)
        val team2Player = team2PlayersList.getOrNull(i)

        players.add(Players(firstTeamPlayer = team1Player, secondTeamPlayer = team2Player))
    }

    return players
}