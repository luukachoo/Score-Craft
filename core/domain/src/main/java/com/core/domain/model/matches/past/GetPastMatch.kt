package com.core.domain.model.matches.past

import com.core.domain.model.matches.live.GetMatchDetails
import com.core.domain.model.matches.live.GetOpponentWrapper
import com.core.domain.model.matches.upcoming.GetVideoGame

data class GetPastMatch(
    val results: List<GetMatchDetails.GetResult>,
    val id: Int,
    val opponents: List<GetOpponentWrapper>,
    val beginAt: String?,
    val name: String?,
    val winner: GetWinner,
    val videoGame: GetVideoGame,
    val winnerId : Int
)