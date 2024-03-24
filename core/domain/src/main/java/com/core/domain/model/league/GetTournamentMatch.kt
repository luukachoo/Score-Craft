package com.core.domain.model.league

import com.core.domain.model.match.GetOpponentWrapper
import com.core.domain.model.match.GetWinner

data class GetTournamentMatch(
    val results: List<GetResult>,
    val slug: String,
    val opponents: List<GetOpponentWrapper>,
    val name: String
)
