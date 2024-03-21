package com.core.domain.model.league

import com.core.domain.model.match.GetOpponentWrapper
import com.core.domain.model.match.GetWinner

data class GetTournamentMatch(
    val results: List<GetResult>,
    val slug: String,
    val winner: GetWinner,
    val opponents: List<GetOpponentWrapper>,
    val winnerId: Int,
    val name: String
)
