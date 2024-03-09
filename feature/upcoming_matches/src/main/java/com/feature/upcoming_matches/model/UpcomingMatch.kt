package com.feature.upcoming_matches.model

import com.core.domain.model.matches.live.GetOpponentWrapper

data class UpcomingMatch(
    val results: List<Result>,
    val id: Int,
    val videogame: VideoGame,
    val opponents: List<GetOpponentWrapper>,
    val beginAt: String,
    val name: String
)
