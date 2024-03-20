package com.example.tournament.event.tournament_list

interface TournamentNavigationEvents {
    data class NavigateToDetails(val slug: String) : TournamentNavigationEvents
}