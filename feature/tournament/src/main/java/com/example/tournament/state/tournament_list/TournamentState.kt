package com.example.tournament.state.tournament_list

import com.example.tournament.model.Tournament

data class TournamentState(
    val tournaments: List<Tournament> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
