package com.example.tournament.state.tournament_matches

import com.example.tournament.model.TournamentMatch

data class TournamentMatchesState(
    val matches: List<TournamentMatch> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
