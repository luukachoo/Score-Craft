package com.example.tournament.event.tournament_details

sealed class TournamentDetailsEvent {
    data class FetchTournamentDetailsBySlug(val slug: String) : TournamentDetailsEvent()
    data object ResetErrorMessage : TournamentDetailsEvent()
    data object BackButtonClick : TournamentDetailsEvent()
}