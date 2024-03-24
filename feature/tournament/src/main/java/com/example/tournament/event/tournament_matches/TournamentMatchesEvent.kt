package com.example.tournament.event.tournament_matches

sealed class TournamentMatchesEvent {
    data class FetchTournamentMatches(val slug: String) : TournamentMatchesEvent()
    data object ResetErrorMessage : TournamentMatchesEvent()
}