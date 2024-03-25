package com.example.tournament.screen.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.tournament.GetTournamentMatchesUseCase
import com.example.tournament.event.tournament_matches.TournamentMatchesEvent
import com.example.tournament.mapper.toPresentationModel
import com.example.tournament.state.tournament_matches.TournamentMatchesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentMatchesViewModel @Inject constructor(
    private val getTournamentMatchesUseCase: GetTournamentMatchesUseCase
) : ViewModel() {

    private val _tournamentMatchesState = MutableStateFlow(TournamentMatchesState())
    val tournamentMatchesState get() = _tournamentMatchesState.asStateFlow()

    fun onEvent(event: TournamentMatchesEvent) {
        when (event) {
            is TournamentMatchesEvent.FetchTournamentMatches -> fetchTournamentMatches(slug = event.slug)
            TournamentMatchesEvent.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchTournamentMatches(slug: String) {
        viewModelScope.launch {
            getTournamentMatchesUseCase(slug).collect { res ->
                res.takeIfSuccess { data ->
                    _tournamentMatchesState.update {
                        it.copy(
                            matches = data.map { getTournamentMatch -> getTournamentMatch.toPresentationModel() },
                            errorMessage = null
                        )
                    }
                }

                res.takeIfLoading { loading(it) }

                res.takeIfError { updateErrorMessage(it) }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _tournamentMatchesState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _tournamentMatchesState.update { it.copy(errorMessage = message) }
}