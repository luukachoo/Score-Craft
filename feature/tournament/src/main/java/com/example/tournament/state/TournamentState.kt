package com.example.tournament.state

import com.example.tournament.model.Tournament

data class TournamentState(
    val tournaments: List<Tournament> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
