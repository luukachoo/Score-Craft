package com.core.domain.model.league

data class GetTournamentsList(
    val getTournaments: List<GetTournament>
) {
    data class GetTournament(
        val beginAt: String,
        val detailedStats: Boolean,
        val endAt: String,
        val hasBracket: Boolean,
        val id: Int,
        val leagueId: Int,
        val liveSupported: Boolean,
        val modifiedAt: String,
        val name: String,
        val prizePool: String,
        val serieId: Int,
        val slug: String,
        val tier: String,
        val winnerId: Int,
        val winnerType: String
    )
}
