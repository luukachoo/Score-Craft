package com.example.tournament.state.tournament_team_standings

import com.example.tournament.model.TeamStanding

data class TournamentTeamStandingsState(
    val teams: List<TeamStanding> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
