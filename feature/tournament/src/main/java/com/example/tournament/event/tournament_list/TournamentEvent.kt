package com.example.tournament.event.tournament_list

sealed class TournamentEvent {
    data class FetchTournaments(val slug: String) : TournamentEvent()
    data class ItemClick(val slug: String) : TournamentEvent()
    data object ResetErrorMessage : TournamentEvent()
}