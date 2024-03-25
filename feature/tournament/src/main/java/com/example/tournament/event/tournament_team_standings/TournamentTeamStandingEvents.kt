package com.example.tournament.event.tournament_team_standings

sealed class TournamentTeamStandingEvents {
    data class FetchTeamStandings(val slug: String) : TournamentTeamStandingEvents()
    data object ResetErrorMessage : TournamentTeamStandingEvents()
}