package com.example.tournament.event

sealed class TournamentEvent {
    data class FetchTournaments(val slug: String) : TournamentEvent()
    data class ItemClick(val slug: String) : TournamentEvent()
    data object ResetErrorMessage : TournamentEvent()
}