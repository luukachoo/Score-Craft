package com.example.tournament.state.tournament_details

import com.example.tournament.model.Tournament

data class TournamentDetailsState(
    val tournamentDetails: Tournament? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
