package com.feature.live_match_details.mapper

import com.core.domain.model.GetOpponentWithType
import com.feature.live_match_details.model.OpponentWithType

fun GetOpponentWithType.toPresentationModel() =
    OpponentWithType(opponents = opponents.map { it.toPresentationModel() })

fun GetOpponentWithType.GetOpponentWrapper.GetOpponent.toPresentationModel() =
    OpponentWithType.OpponentWrapper.Opponent(
        id = id,
        imageUrl = imageUrl,
        name = name,
        slug = slug
    )

//fun GetOpponentWithType.toPlayersList(): List<Players> {
//    return opponents.map { opponent ->
//        Players(
//            firstTeamPlayer = opponent.players.first().toPresentationModel(),
//            secondTeamPlayer = opponent.players.last().toPresentationModel()
//        )
//    }
//}


