package com.core.domain.model.match

import com.core.domain.model.match.live.GetLiveMatchDetails

data class GetMatch(
    val results: List<GetLiveMatchDetails.GetResult>,
    val id: Int,
    val opponents: List<GetOpponentWrapper>,
    val beginAt: String?,
    val name: String?,
    val winner: GetWinner?,
    val videoGame: GetVideoGame,
    val winnerId: Int?
)