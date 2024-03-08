package com.feature.live_match_details.extension

import com.feature.live_match_details.model.Players
import com.feature.live_match_details.model.TeamWrapper


fun TeamWrapper.toPlayersModel(): List<Players> {
    val playersList = mutableListOf<Players>()
    opponents.forEach { opponent ->
        opponent.players.forEachIndexed { index1, player1 ->
            opponent.players.subList(index1 + 1, opponent.players.size).forEach { player2 ->
                playersList.add(
                    Players(
                        firstTeamPlayer = player1,
                        secondTeamPlayer = player2
                    )
                )
            }
        }
    }
    return playersList
}

