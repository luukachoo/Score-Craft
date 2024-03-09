package com.core.domain.model.matches.upcoming

import com.core.domain.model.matches.live.GetMatchDetails
import com.core.domain.model.matches.live.GetOpponentWrapper

data class GetUpcomingMatch (
    val results: List<GetMatchDetails.GetResult>,
    val id: Int,
    val videogame: GetVideoGame,
    val opponents: List<GetOpponentWrapper>,
    val beginAt: String,
    val name: String
)